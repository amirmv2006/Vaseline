package ir.amv.os.vaseline.service.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api.IModelConverter;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.validation.IEntitySearchValidation;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.validation.IEntityShowValidation;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseModelReadApi;
import ir.amv.os.vaseline.service.basic.def.server.base.IDefaultService;
import ir.amv.os.vaseline.service.basic.def.server.exc.ValidationRawException;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface IDefaultEntityReadOnlyService<
            I extends Serializable,
            D extends IBaseDto<I>,
            M extends IBaseBusinessModel<I>,
            A extends IBaseModelReadApi<M>
        >
        extends IDefaultService, IModelConverter<D, M> {

    A getApi();

    default Class<? extends M> getBusinessModelClass() {
        return getApi().getModelClass();
    }

    default Class<D> getDtoClass() {
        return GenericUtils.getGeneric(getClass(), IDefaultEntityReadOnlyService.class, 1);
    }

    // BASE METHODS
    default M convertTo(D d, Class<?>... validationGroups) throws ValidationRawException {
        validate(d, validationGroups);
        return convertTo(d);
    }

    default List<M> convertTo(Collection<D> list, Class<?>... validationGroups) throws ValidationRawException {
        if (list == null) {
            return Collections.emptyList();
        }
        for (D d : list) {
            validate(d, validationGroups);
        }
        List<M> result = new ArrayList<>();
        list.forEach(d -> result.add(convertTo(d)));
        return result;
    }

    default D convertFrom(M e, Class<?>... validationGroups) throws ValidationRawException {
        validate(e, validationGroups);
        return convertFrom(e);
    }

    default List<D> convertFrom(Collection<M> list, Class<?>... validationGroups) throws ValidationRawException {
        if (list == null) {
            return Collections.emptyList();
        }
        for (M m : list) {
            validate(m, validationGroups);
        }
        List<D> result = new ArrayList<>();
        list.forEach(d -> result.add(convertFrom(d)));
        return result;
    }

    default Page<D> convertFrom(Page<M> page, Class<?>... validationGroups) throws ValidationRawException {
        for (M m : page.getContent()) {
            validate(m, validationGroups);
        }
        return page.map(this::convertFrom);
    }

    default Class<?>[] validationGroupsForShow() {
        return new Class<?>[]{IEntityShowValidation.class};
    }

    default Class<?>[] validationGroupsForSearch() {
        return new Class<?>[]{IEntitySearchValidation.class};
    }

}
