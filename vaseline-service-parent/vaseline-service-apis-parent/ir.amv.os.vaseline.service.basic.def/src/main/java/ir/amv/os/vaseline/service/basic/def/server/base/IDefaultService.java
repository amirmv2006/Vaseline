package ir.amv.os.vaseline.service.basic.def.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.core.api.shared.util.ds.list.IVaselineConvertableList;
import ir.amv.os.vaseline.basics.mapper.api.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.basics.validation.api.server.exc.VaselineValidationServerException;
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
            VaselineConvertException, VaselineValidationServerException {
        validate(source, validationGroups);
        return source == null ? null : getMapper().map(source, destinationClass);
    }

    default <S> void validate(S source, Class<?>... validationGroups) throws VaselineConvertException, VaselineValidationServerException {
        if (source != null) {
            Set<ConstraintViolation<S>> validate = getValidator().validate(source, validationGroups);
            if (!validate.isEmpty()) {
                throw new VaselineValidationServerException(new HashSet<ConstraintViolation<?>>(validate));
            }
        }
    }

    default <S, D> List<D> convertList(Collection<S> source,
                                       Class<D> destinationClass, Class<?>... validationGroups) throws VaselineConvertException, VaselineValidationServerException {
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

    default BaseVaselineClientException convertException(Exception e) {
        return getCoreExceptionHandler().convertException(e);
    }

}
