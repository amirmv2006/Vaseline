package ir.amv.os.vaseline.service.basic.def.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.basics.core.api.shared.util.ds.list.IVaselineConvertableList;
import ir.amv.os.vaseline.basics.mapper.api.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.service.basic.api.validation.exc.ValidationException;
import ir.amv.os.vaseline.service.basic.api.server.base.IBaseService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IDefaultService
        extends IBaseService {

    IVaselineObjectMapper getMapper();
    Validator getValidator();
    ICoreExceptionHandler getCoreExceptionHandler();


    default <S, D> D convert(S source, Class<D> destinationClass, Class<?>... validationGroups) throws
            VaselineConvertException, ValidationException {
        validate(source, validationGroups);
        return source == null ? null : getMapper().map(source, destinationClass);
    }

    default <S> void validate(S source, Class<?>... validationGroups) throws VaselineConvertException, ValidationException {
        if (source != null) {
            Set<ConstraintViolation<S>> validate = getValidator().validate(source, validationGroups);
            if (!validate.isEmpty()) {
                throw new ValidationException(new HashSet<ConstraintViolation<?>>(validate));
            }
        }
    }

    default <S, D> List<D> convertList(Collection<S> source,
                                       Class<D> destinationClass, Class<?>... validationGroups) throws VaselineConvertException, ValidationException {
        if (source == null) {
            return null;
        }
        List<D> destList;
        if (source instanceof IVaselineConvertableList) {
            IVaselineConvertableList convertableList = (IVaselineConvertableList) source;
            destList = convertableList.createConvertedList();
        } else {
            destList = new ArrayList<D>();
        }
        for (S s : source) {
            D convert = convert(s, destinationClass, validationGroups);
            destList.add(convert);
        }
        return destList;
    }

    default BaseExternalException convertException(Exception e) {
        return getCoreExceptionHandler().convertException(e);
    }

}
