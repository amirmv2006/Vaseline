package ir.amv.os.vaseline.data.itest;

import io.cucumber.java.en.Given;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = VaselineDataITestConfig.class, loader = SpringBootContextLoader.class)
public class SpringSetup {
    @Given("Spring setup with embedded DB")
    public void springSetupWithEmbeddedDB() {
    }
}
