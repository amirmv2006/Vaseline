package ir.amv.os.vaseline.business.spring.itest.domain.city.impl;

import ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.impl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultConverterSpringApi;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultModelConverter;
import ir.amv.os.vaseline.business.spring.advanced.search.IDefaultAdvancedSearchApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityAdvancedSearchApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessModel;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityAdvancedSearchRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCitySearchObject;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.core.convert.ConversionService;

public class TestCityAdvancedSearchApiImpl
        extends ProxyAwareImpl
        implements ITestCityAdvancedSearchApi,
        IDefaultAdvancedSearchApi<Long, TestCityBusinessModel, TestCityEntity, ITestCitySearchObject, ITestCityAdvancedSearchRepo>,
        IDefaultModelConverter<TestCityBusinessModel, TestCityEntity>,
        IDefaultConverterSpringApi {

    private final ConversionService conversionService;
    private final ITestCityAdvancedSearchRepo repository;

    public TestCityAdvancedSearchApiImpl(ConversionService conversionService, ITestCityAdvancedSearchRepo repository) {
        this.conversionService = conversionService;
        this.repository = repository;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public ITestCityAdvancedSearchRepo getRepository() {
        return repository;
    }
}
