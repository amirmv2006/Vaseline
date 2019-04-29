package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.ops4j.pax.exam.ExamSystem;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.TestAddress;
import org.ops4j.pax.exam.TestContainer;
import org.ops4j.pax.exam.container.remote.RBCRemoteTarget;
import org.ops4j.pax.exam.karaf.container.internal.KarafTestContainer;
import org.ops4j.pax.exam.karaf.container.internal.KarafTestContainerFactory;
import org.ops4j.pax.exam.options.extra.WorkingDirectoryOption;
import org.ops4j.pax.exam.rbc.client.RemoteBundleContextClient;
import org.ops4j.pax.exam.spi.DefaultExamSystem;
import org.ops4j.pax.exam.spi.intern.StepProbeBuilderImpl;
import org.ops4j.store.intern.TemporaryStore;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.ops4j.pax.exam.spi.DefaultExamSystem.createTempDir;

public class RemoteKarafEnvironmentImpl
        implements IRemoteKarafEnvironment {

    private StepProbeBuilderImpl stepProbeBuilder;
    private List<Option> options;
    private Hashtable<Class<?>, Object> cachedLocalObjects;

    private TestContainer[] testContainers;
    private RemoteBundleContextClient clientRBC;

    public RemoteKarafEnvironmentImpl() {
    }

    @Override
    public void init() {
        cachedLocalObjects = new Hashtable<>();
        WorkingDirectoryOption work = new WorkingDirectoryOption(createTemp(null).getAbsolutePath());
        File workingDirectory = createTemp(new File(work.getWorkingDirectory()));
        TemporaryStore store = new TemporaryStore(workingDirectory, false);
        stepProbeBuilder = new StepProbeBuilderImpl(workingDirectory, store);
        options = new ArrayList<>();
    }

    @Override
    public void addStepDefClass(Class<?> stepDefClass, Method method) {
        if (isLocalMethod(method)) {
            cachedLocalObjects.computeIfAbsent(stepDefClass, aClass -> {
                try {
                    return aClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("can not instantiate " + aClass, e);
                }
            });
        } else {
            stepProbeBuilder.addStepDef(stepDefClass, method.getName());
        }
    }

    private boolean isLocalMethod(Method method) {
        return method.isAnnotationPresent(SetupKaraf.class);
    }

    @Override
    public void startKaraf() {
        try {
            KarafTestContainerFactory containerFactory = new KarafTestContainerFactory();
            Option[] config = options.toArray(new Option[0]);
            ExamSystem examSystem = DefaultExamSystem.create(config);
            testContainers = containerFactory.create(examSystem);
            for (TestContainer testContainer : testContainers) {
                testContainer.start();
                testContainer.installProbe(stepProbeBuilder.build().getStream());
                if (testContainer instanceof KarafTestContainer) {
                    KarafTestContainer karafTestContainer = (KarafTestContainer) testContainer;
                    Field target = KarafTestContainer.class.getDeclaredField("target");
                    target.setAccessible(true);
                    RBCRemoteTarget o = (RBCRemoteTarget) target.get(karafTestContainer);
                    clientRBC = o.getClientRBC();
                }
            }
            System.out.println("Started");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stopKaraf() {
        for (TestContainer testContainer : testContainers) {
            testContainer.stop();
        }
    }

    @Override
    public void addOption(Option option) {
        this.options.add(option);
    }

    @Override
    public void addOptions(List<Option> options) {
        this.options.addAll(options);
    }

    @Override
    public <O> O getProxiedRemoteInstance(Class<O> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((InvocationHandler) (o, method, args) -> {
            if (isLocalMethod(method)) {
                return method.invoke(cachedLocalObjects.get(clazz), args);
            }
            String invocationCaption = method.getDeclaringClass().getName() + "." + method.getName();
            TestAddress address = stepProbeBuilder.getTests().stream()
                    .filter(testAddr -> testAddr.caption().equals(invocationCaption))
                    .findFirst().orElseThrow(() -> new RuntimeException("Can not find address"));
            return getClientRBC().callStep(address, args);
        });
        return (O) enhancer.create();
    }

    private RemoteBundleContextClient getClientRBC() {
//        if (clientRBC == null) {
//            startKaraf();
//        }
        return clientRBC;
    }

    private synchronized File createTemp(File workingDirectory) {
        if (workingDirectory == null) {
            return createTempDir();
        } else {
            workingDirectory.mkdirs();
            return workingDirectory;
        }
    }
}