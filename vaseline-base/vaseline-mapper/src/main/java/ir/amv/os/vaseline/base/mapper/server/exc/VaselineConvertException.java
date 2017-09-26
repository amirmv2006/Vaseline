package ir.amv.os.vaseline.base.mapper.server.exc;

import ir.amv.os.vaseline.basics.apis.core.api.server.base.exc.BaseVaselineServerException;
import org.dozer.MappingException;

/**
 * Created by AMV on 1/16/2016.
 */
public class VaselineConvertException extends BaseVaselineServerException {

    private MappingException mappingException;

    public VaselineConvertException(MappingException mappingException) {
        this.mappingException = mappingException;
    }

    public MappingException getMappingException() {
        return mappingException;
    }
}
