package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import ir.amv.os.vaseline.testing.integration.cucumber.karaf.listener.IRemoteObjectListener;
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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import static org.ops4j.pax.exam.spi.DefaultExamSystem.createTempDir;

public class RemoteKarafEnvironmentImpl
        implements IRemoteKarafEnvironment {
    private List<IRemoteObjectListener> listeners;

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
        WorkingDirectoryOption work =
                new WorkingDirectoryOption(createTemp(new File("target/" + UUID.randomUUID().toString())).getAbsolutePath());
        File workingDirectory = createTemp(new File(work.getWorkingDirectory()));
        TemporaryStore store = new TemporaryStore(workingDirectory, false);
        stepProbeBuilder = new StepProbeBuilderImpl(workingDirectory, store);
        options = new ArrayList<>();
        listeners = new ArrayList<>();
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

    @Override
    public void addExtraClasses(Class<?>... classes) {
        stepProbeBuilder.addExtraClasses(classes);
    }

    private boolean isLocalMethod(Method method) {
        return method.isAnnotationPresent(RunLocally.class);
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
                    RBCRemoteTarget o = forceGetField(karafTestContainer, "target");
                    clientRBC = o.getClientRBC();
                    File targetFolder = forceGetField(karafTestContainer, "targetFolder");
                    System.out.println("Karaf started in " + targetFolder.getName());
                }
            }
            System.out.println("Started");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <O> O forceGetField(KarafTestContainer karafTestContainer, String field) throws NoSuchFieldException, IllegalAccessException {
        Field target = KarafTestContainer.class.getDeclaredField(field);
        target.setAccessible(true);
        return (O) target.get(karafTestContainer);
    }

    @Override
    public void stopKaraf() {
        for (TestContainer testContainer : testContainers) {
            testContainer.stop();
        }
        listeners.clear();

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
            Object result = getClientRBC().callStep(address, args);
            listeners.forEach(l -> l.remoteMethodInvoked(o, method, args));
            return result;
        });
        O proxy = (O) enhancer.create();
        listeners.forEach(l -> l.remoteObjectCreated(clazz, proxy));
        return proxy;
    }

    @Override
    public List<Class<?>> getAllRemoteClasses() {
        return stepProbeBuilder.getAllRemoteClasses();
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

    @Override
    public void addRemoteObjectListener(IRemoteObjectListener listener) {
        listeners.add(listener);
    }
}
