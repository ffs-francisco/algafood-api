package com.ffs.api.controller;

import com.ffs.api.controller.model.KitchensXMLWrapper;
import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping
    public List<Kitchen> listAll() {
        return this.kitchenRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXMLWrapper listAllXML() {
        return new KitchensXMLWrapper(this.kitchenRepository.findAll());
    }

    @GetMapping("/{kitchenId}")
    public Kitchen findById(@PathVariable Long kitchenId) {
        return this.kitchenRepository.findById(kitchenId);
    }
}
