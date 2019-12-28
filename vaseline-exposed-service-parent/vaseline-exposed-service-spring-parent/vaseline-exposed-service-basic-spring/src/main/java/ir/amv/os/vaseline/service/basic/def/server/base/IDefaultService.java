package ir.amv.os.vaseline.service.basic.def.server.base;

import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api.IConverterApi;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.exc.handler.impl.IDefaultExceptionHandlingConverterImpl;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.base.IBaseService;
import ir.amv.os.vaseline.service.basic.def.server.exc.ValidationRawException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

public interface IDefaultService
        extends IBaseService, IConverterApi,
        IDefaultExceptionHandlingConverterImpl<BaseBusinessException, BaseExternalException> {

    Validator getValidator();

    default <S> void validate(S source, Class<?>... validationGroups) throws ValidationRawException {
        if (source != null) {
            Set<ConstraintViolation<S>> validate = getValidator().validate(source, validationGroups);
            if (!validate.isEmpty()) {
                throw new ValidationRawException(new HashSet<>(validate));
            }
        }
    }

}
