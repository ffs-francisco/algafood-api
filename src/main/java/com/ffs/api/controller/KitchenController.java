package com.ffs.api.controller;

import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
import com.ffs.api.domain.service.KitchenRegistrationService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegistrationService kitchenRegistrationService;

    @GetMapping
    public List<Kitchen> listAll() {
        return this.kitchenRepository.findAll();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long kitchenId) {
        var kitchen = this.kitchenRepository.findById(kitchenId);

        return (kitchen.isPresent()) ? ResponseEntity.ok(kitchen.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return this.kitchenRegistrationService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    @ResponseStatus(CREATED)
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchenParam) {
        var kitchen = this.kitchenRepository.findById(kitchenId);

        if (kitchen.isPresent()) {
            BeanUtils.copyProperties(kitchenParam, kitchen.get(), "id");

            return ResponseEntity.ok(this.kitchenRegistrationService.save(kitchen.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long kitchenId) {
        kitchenRegistrationService.delete(kitchenId);
    }
}
