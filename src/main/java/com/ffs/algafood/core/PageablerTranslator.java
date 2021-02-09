package com.ffs.algafood.core;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author francisco
 */
public class PageablerTranslator {

    public static Pageable translate(Pageable pageable, Map<String, String> mapping) {
        var orders = pageable.getSort().stream()
                .map(order -> new Sort.Order(order.getDirection(), mapping.get(order.getProperty())))
                .collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }

}
