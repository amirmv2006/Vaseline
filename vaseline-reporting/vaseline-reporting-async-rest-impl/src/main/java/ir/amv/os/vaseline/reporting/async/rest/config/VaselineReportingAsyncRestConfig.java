package ir.amv.os.vaseline.reporting.async.rest.config;

import ir.amv.os.vaseline.basics.spring.mapper.config.custconv.BaseVaselineCustomConverterClassHolderImpl;
import ir.amv.os.vaseline.basics.spring.mapper.config.custconv.IVaselineCustomConverterClassHolder;
import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import ir.amv.os.vaseline.basics.apis.core.server.polymorphysm.defimpl.VaselinePolymorphysmClassHolderImpl;
import ir.amv.os.vaseline.reporting.api.shared.model.IBaseReportSourceClient;
import ir.amv.os.vaseline.reporting.async.rest.server.dozer.VaselineFileReportSourceCustomConverter;
import ir.amv.os.vaseline.reporting.async.rest.server.dozer.VaselineProjectResourceReportSourceCustomConverter;
import ir.amv.os.vaseline.ws.rest.config.VaselineWebServiceRestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by AMV on 2/14/2016.
 */
@Configuration
@ComponentScan("ir.amv.os.vaseline.reporting.async.rest.server")
@Import(
        VaselineWebServiceRestConfig.class
)
public class VaselineReportingAsyncRestConfig {

    @Bean
    public IVaselinePolymorphysmClassHolder reportingPolymorphysmClassHolder() {
        return new VaselinePolymorphysmClassHolderImpl(IBaseReportSourceClient.class);
    }

    @Bean
    public IVaselineCustomConverterClassHolder reportingAsyncRestCustomConverts() {
        return new BaseVaselineCustomConverterClassHolderImpl() {
            @Override
            public Class<?>[] customConverterClasses() {
                return new Class<?>[]{
                        VaselineFileReportSourceCustomConverter.class,
                        VaselineProjectResourceReportSourceCustomConverter.class
                };
            }
        };
    }
}
