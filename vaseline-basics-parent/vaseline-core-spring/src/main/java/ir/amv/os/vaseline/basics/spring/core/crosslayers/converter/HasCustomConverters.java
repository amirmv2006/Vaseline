package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.ConverterRegistry;

public interface HasCustomConverters {

    @Autowired
    void registerConverters(final ConverterRegistry converterRegistry);
}
