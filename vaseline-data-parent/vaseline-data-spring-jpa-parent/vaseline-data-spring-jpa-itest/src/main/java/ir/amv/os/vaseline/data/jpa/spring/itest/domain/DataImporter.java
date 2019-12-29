package ir.amv.os.vaseline.data.jpa.spring.itest.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class DataImporter {
    private static final String CONTINENT_NAME_COL = "ContinentName";
    private static final String COUNTRY_NAME_COL = "CountryName";
    private static final String COUNTRY_POPULATION_COL = ".CountryPopulation";
    private static final String COUNTRY_BIRTH_DATE_COL = "CountryBirthDate";
    private static final String STATE_NAME_COL = "StateName";
    private static final String CITY_NAME_COL = "CityName";

    private final JdbcTemplate jdbcTemplate;

    public DataImporter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void importData(List<Map<String, String>> data) {
        // delete data
        jdbcTemplate.update("delete from tst_city");
        jdbcTemplate.update("delete from tst_state");
        jdbcTemplate.update("delete from tst_country");
        jdbcTemplate.update("delete from tst_continent");

        AtomicLong idGen = new AtomicLong();
        Map<String, Long> continentIdMap = new HashMap<>();
        Map<String, Long> countryIdMap = new HashMap<>();
        Map<String, Long> stateIdMap = new HashMap<>();
        for (Map<String, String> row : data) {
            Map<String, Object> continent = new HashMap<>();
            Map<String, Object> country = new HashMap<>();
            Map<String, Object> state = new HashMap<>();
            Map<String, Object> city = new HashMap<>();
            Optional.ofNullable(row.get(CONTINENT_NAME_COL))
                    .map(this::emptyToNull)
                    .map(continentName -> continentIdMap.computeIfAbsent(continentName, s -> {
                        long id = idGen.incrementAndGet();
                        continent.put("id", id);
                        continent.put("continent_name", s);
                        insertByMap("tst_continent", continent);
                        return id;
                    })).ifPresent(id -> country.put("continent_id", id));
            Optional.ofNullable(row.get(COUNTRY_POPULATION_COL))
                    .map(this::emptyToNull)
                    .ifPresent(pop -> country.put("population", pop));
            Optional.ofNullable(row.get(COUNTRY_BIRTH_DATE_COL))
                    .map(this::emptyToNull)
                    .ifPresent(bd -> country.put("birth_date", bd));
            Optional.ofNullable(row.get(COUNTRY_NAME_COL))
                    .map(this::emptyToNull)
                    .map(countryName -> countryIdMap.computeIfAbsent(countryName, s -> {
                        long id = idGen.incrementAndGet();
                        country.put("id", id);
                        country.put("country_name", s);
                        insertByMap("tst_country", country);
                        return id;
                    })).ifPresent(id -> state.put("country_id", id));
            Optional.ofNullable(row.get(STATE_NAME_COL))
                    .map(this::emptyToNull)
                    .map(stateName -> stateIdMap.computeIfAbsent(stateName, s -> {
                        long id = idGen.incrementAndGet();
                        state.put("id", id);
                        state.put("state_name", s);
                        insertByMap("tst_state", state);
                        return id;
                    })).ifPresent(id -> city.put("state_id", id));
            Optional.ofNullable(row.get(CITY_NAME_COL))
                    .map(this::emptyToNull)
                    .ifPresent(cityName -> {
                        city.put("id", idGen.incrementAndGet());
                        city.put("city_name", cityName);
                        insertByMap("tst_city", city);
                    });
        }
    }

    private String emptyToNull(String str) {
        return Optional.of(str).filter(s -> !s.isEmpty()).orElse(null);
    }

    private void insertByMap(String tableName, Map<String, Object> columnMap) {
        List<String> columnNames = columnMap.keySet().stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder("insert into ");
        sb.append(tableName);
        sb.append("(");
        sb.append(String.join(", ", columnNames));
        sb.append(") values (");
        sb.append(columnNames.stream().map(ignored -> "?").collect(Collectors.joining(", ")));
        sb.append(")");
        jdbcTemplate.update(sb.toString(), columnNames.stream()
                .map(columnMap::get)
                .toArray()
        );
    }

}
