package ir.amv.os.vaseline.service.rest.itest.advanced.search;

import ir.amv.os.vaseline.business.spring.itest.advanced.search.VaselineITestBusinessAdvancedSearchConfig;
import ir.amv.os.vaseline.service.rest.itest.domain.ITestRestSharedConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({VaselineITestBusinessAdvancedSearchConfig.class, ITestRestSharedConfig.class})
public class VaselineITestRestAdvancedSearchConfig {

}
