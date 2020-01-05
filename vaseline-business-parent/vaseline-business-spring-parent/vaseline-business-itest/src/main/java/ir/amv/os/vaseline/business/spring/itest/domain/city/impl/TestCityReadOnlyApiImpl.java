package ir.amv.os.vaseline.business.spring.itest.domain.city.impl;

import ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.impl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultConverterSpringApi;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultModelConverter;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.spring.basic.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityReadOnlyApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessEntity;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityReadOnlyRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class TestCityReadOnlyApiImpl
        extends ProxyAwareImpl
        implements ITestCityReadOnlyApi,
        IDefaultReadOnlyApi<Long, TestCityBusinessEntity, TestCityEntity, ITestCityReadOnlyRepo>,
        IDefaultModelConverter<TestCityBusinessEntity, TestCityEntity>,
        IDefaultConverterSpringApi {

    private final ConversionService conversionService;
    private final ITestCityReadOnlyRepo repository;

    public TestCityReadOnlyApiImpl(ConversionService conversionService, ITestCityReadOnlyRepo repository) {
        this.conversionService = conversionService;
        this.repository = repository;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public ITestCityReadOnlyRepo getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public List<TestCityBusinessEntity> getCitiesOfState(String stateName) throws BaseBusinessException {
        List<TestCityBusinessEntity> result = new ArrayList<>();
        try {
            repository.findByStateName(stateName).get().forEach(pm -> result.add(convertFrom(pm)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        postGetList(result);
        return result;
    }
}
