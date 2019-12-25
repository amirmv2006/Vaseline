package ir.amv.os.vaseline.data.jpa.spring.itest.advanced.search;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = VaselineDataAdvancedSearchITestConfig.class, loader = SpringBootContextLoader.class)
public class SpringSetup {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Given("Spring setup with embedded DB with cities")
    public void springSetupWithEmbeddedDB(DataTable dataTable) {
        jdbcTemplate.update("delete from tst_city");
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        AtomicInteger idGen = new AtomicInteger();
        data.stream()
                .map(map -> map.get("CityName"))
                .forEach(cityName -> jdbcTemplate.update(
                        "insert into tst_city(id, city_name) values (?, ?)", idGen.incrementAndGet(), cityName
                ));
    }
}
