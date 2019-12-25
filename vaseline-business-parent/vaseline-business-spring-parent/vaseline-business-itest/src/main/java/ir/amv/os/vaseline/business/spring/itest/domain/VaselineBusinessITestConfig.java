package ir.amv.os.vaseline.business.spring.itest.domain;

import ir.amv.os.vaseline.data.jpa.spring.itest.advanced.search.VaselineDataAdvancedSearchITestConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(VaselineDataAdvancedSearchITestConfig.class)
public class VaselineBusinessITestConfig {
}
