package ir.amv.os.vaseline.data.test.model.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCityEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestContinentEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.server.entity.TestStateEntity;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCityRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestContinentRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestCountryRepository;
import ir.amv.os.vaseline.data.test.model.server.repository.ITestStateRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.inject.Inject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BaseDataModelTest {

    @Inject
    protected ITestContinentRepository continentRepository;
    @Inject
    protected ITestCountryRepository countryRepository;
    @Inject
    protected ITestStateRepository stateRepository;
    @Inject
    protected ITestCityRepository cityRepository;

    protected Map<String, TestContinentEntity> continentsMap = new HashMap<>();
    protected Map<String, TestCountryEntity> countriesMap = new HashMap<>();
    protected Map<String, TestStateEntity> statesMap = new HashMap<>();
    protected Map<String, TestCityEntity> citiesMap = new HashMap<>();

    public void setupDataFromJson(InputStream is) {
        Gson gson = new Gson();
        ArrayList<TestCountryEntity> countries = gson.fromJson(new InputStreamReader(is), new TypeToken<ArrayList<TestCountryEntity>>(){}.getType());
        for (TestCountryEntity country : countries) {
            TestContinentEntity continent = country.getContinent();
            if (!continentsMap.containsKey(continent.getContinentName())) {
                continentRepository.save(continent);
                continentsMap.put(continent.getContinentName(), continent);
            } else {
                country.setContinent(continentsMap.get(continent.getContinentName()));
            }
            Set<TestStateEntity> states = country.getStates();
            for (TestStateEntity state : states) {
                Set<TestCityEntity> cities = state.getCities();
                for (TestCityEntity city : cities) {
                    if (!citiesMap.containsKey(city.getCityName())) {
                        cityRepository.save(city);
                        citiesMap.put(city.getCityName(), city);
                    }
                }
                if (!statesMap.containsKey(state.getStateName())) {
                    stateRepository.save(state);
                    statesMap.put(state.getStateName(), state);
                }
            }
            if (!countriesMap.containsKey(country.getCountryName())) {
                countryRepository.save(country);
                countriesMap.put(country.getCountryName(), country);
            }
        }
    }

    public void tearDownAll() {
        deleteMapItems(countriesMap, countryRepository);
        deleteMapItems(continentsMap, continentRepository);
        deleteMapItems(statesMap, stateRepository);
        deleteMapItems(citiesMap, cityRepository);
    }

    private <E> void deleteMapItems(Map<String, E> countriesMap, PagingAndSortingRepository<E, ?> countryRepository) {
        Set<String> strings = new HashSet<>(countriesMap.keySet());
        for (String countryName : strings) {
            E remove = countriesMap.remove(countryName);
            countryRepository.delete(remove);
        }
    }

}
