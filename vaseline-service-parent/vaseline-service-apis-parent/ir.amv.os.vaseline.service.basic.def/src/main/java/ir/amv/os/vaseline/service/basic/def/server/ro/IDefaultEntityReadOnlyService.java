package ir.amv.os.vaseline.service.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.validation.IEntitySearchValidation;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.validation.IEntityShowValidation;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.mapper.api.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.service.basic.api.validation.exc.ValidationException;
import ir.amv.os.vaseline.basics.logging.common.osgi.server.helper.LOGGER;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.service.basic.def.server.base.IDefaultService;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public interface IDefaultEntityReadOnlyService<Id extends
        Serializable, E extends IBaseBusinessModel<Id>, D extends IBaseDto<Id>, Api extends IBaseEntityReadOnlyApi<E>>
        extends IDefaultService {
    @Deprecated
    Api getApi();

    default Class<Api> apiClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(), IDefaultEntityReadOnlyService.class);
        for (Class<?> genericArgumentClass : genericArgumentClasses) {
            if (IBaseEntityReadOnlyApi.class.isAssignableFrom(genericArgumentClass)) {
                return (Class<Api>) genericArgumentClass;
            }
        }
        return null;
    }

    default Api getApiProxy() throws BaseExternalException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Api> future = executor.submit(() -> {
            Api proxy;
            while ((proxy = getApi().getProxy(apiClass())) == null) {
                LOGGER.log(VaselineLogLevel.WARNING, "Still waiting for %s api proxy to become available",
                        IDefaultEntityReadOnlyService.this.getClass());
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
            return getApiProxy().getModelClass();
        } catch (BaseExternalException e) {
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
            ValidationException {
        return convert(d, getEntityClass(), validationGroups);
    }

    default List<E> convertDtoToEntity(Collection<D> list, Class<?>... validationGroups) throws
            VaselineConvertException, ValidationException {
        return convertList(list, (Class<E>)getEntityClass(), validationGroups);
    }

    default D convertEntityToDTO(E e, Class<?>... validationGroups) throws VaselineConvertException, ValidationException {
        return convert(e, getDtoClass(), validationGroups);
    }

    default List<D> convertEntityToDTO(Collection<E> list, Class<?>... validationGroups) throws VaselineConvertException,
            ValidationException {
        return convertList(list, getDtoClass(), validationGroups);
    }

    default Class<?>[] validationGroupsForShow() {
        return new Class<?>[]{IEntityShowValidation.class};
    }

    default Class<?>[] validationGroupsForSearch() {
        return new Class<?>[]{IEntitySearchValidation.class};
    }

}
