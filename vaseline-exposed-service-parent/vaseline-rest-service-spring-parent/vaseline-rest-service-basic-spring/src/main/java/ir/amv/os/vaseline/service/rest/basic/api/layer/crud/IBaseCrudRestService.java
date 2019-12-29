package ir.amv.os.vaseline.service.rest.basic.api.layer.crud;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.crud.IBaseCrudService;
import ir.amv.os.vaseline.service.rest.basic.api.layer.ro.IBaseReadOnlyRestService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.Serializable;

public interface IBaseCrudRestService<I extends Serializable, D extends IBaseDto<I>>
        extends IBaseCrudService<I, D>, IBaseReadOnlyRestService<I, D> {

    @Override
    @PostMapping
    I save(D t) throws BaseExternalException;

    @Override
    @PutMapping
    void update(D t) throws BaseExternalException;

    @Override
    @PostMapping(path = "delete")
    void delete(D id) throws BaseExternalException;

    @Override
    @DeleteMapping(path = "{id}")
    void deleteById(@PathVariable("id") I id) throws BaseExternalException;
}
