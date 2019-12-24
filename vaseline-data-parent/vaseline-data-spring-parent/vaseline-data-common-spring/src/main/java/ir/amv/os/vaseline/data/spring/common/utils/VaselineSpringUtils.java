package ir.amv.os.vaseline.data.spring.common.utils;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.paging.PagingDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VaselineSpringUtils {
    public static Pageable toSpringPageable(PagingDto pagingDto) {
        Integer pageNumber = pagingDto.getPageNumber();
        Integer pageSize = pagingDto.getPageSize();
        List<Sort.Order> orders = Optional.of(pagingDto)
                .map(PagingDto::getSortList)
                .map(sl -> sl.stream()
                        .map(s -> s.getAscending().equals(Boolean.TRUE) ?
                                Sort.Order.asc(s.getPropertyName()) :
                                Sort.Order.desc(s.getPropertyName()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
        Sort sorts = Sort.by(orders);
        return PageRequest.of(pageNumber, pageSize, sorts);
    }
}
