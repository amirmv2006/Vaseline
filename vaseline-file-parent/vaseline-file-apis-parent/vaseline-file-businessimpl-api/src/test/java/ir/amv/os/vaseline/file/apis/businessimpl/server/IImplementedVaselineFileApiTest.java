package ir.amv.os.vaseline.file.apis.businessimpl.server;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestVaselineFileApiSpringConfig.class)
public class IImplementedVaselineFileApiTest
        extends BaseVaselineFileApiTest {

    @BeforeClass
    public static void checkIfDockerEnv() {
        String property = System.getProperty("docker.available");
        Assume.assumeTrue(property == null || property.equals("true"));
    }

}