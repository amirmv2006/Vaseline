package ir.amv.os.vaseline.service.rest.basic.api.layer.ro;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.service.rest.basic.api.layer.base.IBaseRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.List;

public interface IBaseReadOnlyRestService<I extends Serializable, D extends IBaseDto<I>>
        extends IBaseReadOnlyService<I, D>, IBaseRestService {

    @Override
    @GetMapping(path = "/{id}")
    D getById(@PathVariable("id") I id) throws BaseExternalException;

    @Override
    @GetMapping(path = "/count")
    Long countAll() throws BaseExternalException;

    @Override
    @GetMapping(path = "/")
    List<D> getAll() throws BaseExternalException;

    @Override
    @PostMapping(path = "/page")
    Page<D> getAll(Pageable pagingDto) throws BaseExternalException;
}
