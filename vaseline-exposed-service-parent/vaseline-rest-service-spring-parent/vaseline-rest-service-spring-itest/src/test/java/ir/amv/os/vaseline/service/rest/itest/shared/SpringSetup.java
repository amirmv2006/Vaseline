package ir.amv.os.vaseline.service.rest.itest.shared;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.DataImporter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class SpringSetup {

    @Autowired
    DataImporter dataImporter;

    @Given("Spring setup with embedded DB with cities")
    public void springSetupWithEmbeddedDB(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        dataImporter.importData(data);
    }

}
