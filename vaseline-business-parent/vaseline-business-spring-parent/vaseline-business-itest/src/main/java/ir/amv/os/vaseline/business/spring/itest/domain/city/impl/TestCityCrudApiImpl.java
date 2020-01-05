package ir.amv.os.vaseline.business.spring.itest.domain.city.impl;

import ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.impl.ProxyAwareImpl;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultConverterSpringApi;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultModelConverter;
import ir.amv.os.vaseline.business.spring.basic.crud.IDefaultCrudApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.ITestCityCrudApi;
import ir.amv.os.vaseline.business.spring.itest.domain.city.TestCityBusinessEntity;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.ITestCityCrudRepo;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import org.springframework.core.convert.ConversionService;

public class TestCityCrudApiImpl
        extends ProxyAwareImpl
        implements ITestCityCrudApi,
        IDefaultCrudApi<Long, TestCityBusinessEntity, TestCityEntity, ITestCityCrudRepo>,
        IDefaultModelConverter<TestCityBusinessEntity, TestCityEntity>,
        IDefaultConverterSpringApi {

    private final ConversionService conversionService;
    private final ITestCityCrudRepo repository;

    public TestCityCrudApiImpl(ConversionService conversionService, ITestCityCrudRepo repository) {
        this.conversionService = conversionService;
        this.repository = repository;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public ITestCityCrudRepo getRepository() {
        return repository;
    }
}
