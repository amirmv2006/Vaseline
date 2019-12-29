package ir.amv.os.vaseline.service.rest.itest.basic;

import ir.amv.os.vaseline.business.spring.itest.basic.VaselineITestBusinessBasicConfig;
import ir.amv.os.vaseline.service.rest.itest.domain.ITestRestSharedConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({VaselineITestBusinessBasicConfig.class, ITestRestSharedConfig.class})
public class VaselineITestRestBasicConfig {

    public static void main(String[] args) {
        SpringApplication.run(VaselineITestRestBasicConfig.class);
    }

}
