package com.ffs.api.domain.repository;

import com.ffs.api.domain.model.Kitchen;
import java.util.List;

/**
 *
 * @author francisco
 */
public interface KitchenRepository {

    List<Kitchen> findAll();

    Kitchen findById(Long id);

    Kitchen save(Kitchen kitchen);

    void delete(Long id);
}
