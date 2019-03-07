package ir.amv.os.vaseline.ws.spring.rest.jersey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Amir
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VaselineRestJerseyTest.IntegrationConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class VaselineRestJerseyTest {

    @Configuration
    @EnableAutoConfiguration
    public static class IntegrationConfig {
    }

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        System.out.println("port = " + port);
        String body = this.restTemplate.getForObject("/rest/integrationTest", String.class);
        assertThat(body).isEqualTo("[]");
    }
}
