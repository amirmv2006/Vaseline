package ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntitySearchValidation;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntityShowValidation;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.apis.mapper.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.apis.validation.server.exc.VaselineValidationServerException;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.base.IBaseImplementedService;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public interface IBaseImplementedEntityReadOnlyService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Api extends IBaseEntityReadOnlyApi<E>>
        extends IBaseImplementedService {
    @Deprecated
    Api getApi();

    default Class<Api> apiClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), IBaseImplementedEntityReadOnlyService.class);
        for (Class<?> genericArgumentClass : genericArgumentClasses) {
            if (IBaseEntityReadOnlyApi.class.isAssignableFrom(genericArgumentClass)) {
                return (Class<Api>) genericArgumentClass;
            }
        }
        return null;
    }

    default Api getApiProxy() throws BaseVaselineClientException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Api> future = executor.submit(() -> {
            Api proxy;
            while ((proxy = getApi().getProxy(apiClass())) == null) {
                LOGGER.log(VaselineLogLevel.WARNING, "Still waiting for %s api proxy to become available",
                        IBaseImplementedEntityReadOnlyService.this.getClass());
                Thread.sleep(1000);
            }
            return proxy;
        });

        try {
            return future.get();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default Class<? extends E> getEntityClass() {
        try {
            return getApiProxy().getEntityClass();
        } catch (BaseVaselineClientException e) {
            return null;
        }
    }

    default Class<D> getDtoClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClassesDeprecated(getClass());
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
        return convertList(list, (Class<E>)getEntityClass(), validationGroups);
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
