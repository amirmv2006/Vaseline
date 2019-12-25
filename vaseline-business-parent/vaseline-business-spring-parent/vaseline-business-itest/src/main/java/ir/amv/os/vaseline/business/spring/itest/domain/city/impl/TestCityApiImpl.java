package ir.amv.os.vaseline.business.spring.itest.domain.city.impl;

import ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.impl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultConverterSpringApi;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultModelConverter;
import ir.amv.os.vaseline.business.spring.basic.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessModel;
import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityApi;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityAdvancedSearchRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.core.convert.ConversionService;

public class TestCityApiImpl
        extends ProxyAwareImpl
        implements ITestCityApi,
        IDefaultCrudApi<Long, TestCityBusinessModel, TestCityEntity, ITestCityAdvancedSearchRepo>,
        IDefaultModelConverter<TestCityBusinessModel, TestCityEntity>,
        IDefaultConverterSpringApi {

    private final ConversionService conversionService;
    private final ITestCityAdvancedSearchRepo repository;

    public TestCityApiImpl(ConversionService conversionService, ITestCityAdvancedSearchRepo repository) {
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
