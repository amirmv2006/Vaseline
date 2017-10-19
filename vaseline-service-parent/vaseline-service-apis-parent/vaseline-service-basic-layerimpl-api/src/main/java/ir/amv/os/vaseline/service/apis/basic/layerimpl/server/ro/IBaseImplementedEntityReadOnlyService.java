package ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntitySearchValidation;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntityShowValidation;
import ir.amv.os.vaseline.basics.apis.mapper.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.apis.validation.server.exc.VaselineValidationServerException;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.base.IBaseImplementedService;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IBaseImplementedEntityReadOnlyService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable>
        extends IBaseImplementedService {

    default Class<E> getEntityClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseEntity.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<E>) genericArgumentClass;
                }
            }
        }
        return null;
    }

    default Class<D> getDtoClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseDto.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<D>) genericArgumentClass;
                }
            }
        }
        return null;
    }

    // BASE METHODS
    default E convertDtoToEntity(D d, Class<?>... validationGroups) throws VaselineConvertException,
            VaselineValidationServerException {
        return convert(d, getEntityClass(), validationGroups);
    }

    default List<E> convertDtoToEntity(Collection<D> list, Class<?>... validationGroups) throws
            VaselineConvertException, VaselineValidationServerException {
        return convertList(list, getEntityClass(), validationGroups);
    }

    default D convertEntityToDTO(E e, Class<?>... validationGroups) throws VaselineConvertException, VaselineValidationServerException {
        return convert(e, getDtoClass(), validationGroups);
    }

    default List<D> convertEntityToDTO(Collection<E> list, Class<?>... validationGroups) throws VaselineConvertException,
            VaselineValidationServerException {
        return convertList(list, getDtoClass(), validationGroups);
    }

    default Class<?>[] validationGroupsForShow() {
        return new Class<?>[]{IEntityShowValidation.class};
    }

    default Class<?>[] validationGroupsForSearch() {
        return new Class<?>[]{IEntitySearchValidation.class};
    }

}
