package ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.service;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.service.IBaseService;
import ir.amv.os.vaseline.base.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.base.core.shared.util.ds.list.IVaselineConvertableList;
import ir.amv.os.vaseline.base.mapper.server.exc.VaselineConvertException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

public class BaseServiceImpl implements IBaseService {

	protected Mapper mapper;
	protected Validator validator;
	protected ICoreExceptionHandler coreExceptionHandler;
	
	protected <S, D> D convert(S source, Class<D> destinationClass, Class<?>... validationGroups) throws VaselineConvertException {
		// if (source instanceof BaseEntity<?> && hasLazyProp()) {
		// BaseEntity<?> ent = (BaseEntity<?>) source;
		// lazyProxyRemover.removeProxy(ent);
		// }
        validate(source, validationGroups);
        return source == null ? null : mapper.map(source, destinationClass);
	}

    protected <S> void validate(S source, Class<?>... validationGroups) throws VaselineConvertException {
        if (source != null) {
            Set<ConstraintViolation<S>> validate = validator.validate(source, validationGroups);
            if (!validate.isEmpty()) {
                throw new VaselineConvertException(new HashSet<ConstraintViolation<?>>(validate));
            }
        }
    }

    protected <S, D> List<D> convertList(Collection<S> source,
			Class<D> destinationClass, Class<?>... validationGroups) throws VaselineConvertException {
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
	public void setMapper(Mapper mapper) {
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
