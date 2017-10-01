package ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.service;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.service.IBaseService;
import ir.amv.os.vaseline.basics.apis.core.shared.util.ds.list.IVaselineConvertableList;
import ir.amv.os.vaseline.basics.apis.mapper.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.apis.mapper.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.basics.apis.validation.server.exc.VaselineValidationServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseServiceImpl implements IBaseService {

	protected IVaselineObjectMapper mapper;
	protected Validator validator;
	protected ICoreExceptionHandler coreExceptionHandler;
	
	protected <S, D> D convert(S source, Class<D> destinationClass, Class<?>... validationGroups) throws VaselineConvertException, VaselineValidationServerException {
		// if (source instanceof BaseEntity<?> && hasLazyProp()) {
		// BaseEntity<?> ent = (BaseEntity<?>) source;
		// lazyProxyRemover.removeProxy(ent);
		// }
        validate(source, validationGroups);
        return source == null ? null : mapper.map(source, destinationClass);
	}

    protected <S> void validate(S source, Class<?>... validationGroups) throws VaselineConvertException, VaselineValidationServerException {
        if (source != null) {
            Set<ConstraintViolation<S>> validate = validator.validate(source, validationGroups);
            if (!validate.isEmpty()) {
                throw new VaselineValidationServerException(new HashSet<ConstraintViolation<?>>(validate));
            }
        }
    }

    protected <S, D> List<D> convertList(Collection<S> source,
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

	@Autowired
	public void setMapper(IVaselineObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	@Autowired
    @Qualifier("getValidatorFactory")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	@Autowired
	public void setCoreExceptionHandler(
			ICoreExceptionHandler coreExceptionHandler) {
		this.coreExceptionHandler = coreExceptionHandler;
	}

	protected BaseVaselineClientException convertException(Exception e) {
		return coreExceptionHandler.convertException(e);
	}

}
