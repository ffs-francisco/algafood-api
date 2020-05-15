package com.ffs.api.controller;

import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.service.KitchenService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenService kitchenRegistrationService;

    @GetMapping
    @ResponseStatus(OK)
    public List<Kitchen> listAll() {
        return this.kitchenRegistrationService.findAll();
    }

    @GetMapping("/{kitchenId}")
    @ResponseStatus(OK)
    public Kitchen findById(@PathVariable Long kitchenId) {
        return kitchenRegistrationService.findById(kitchenId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return this.kitchenRegistrationService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    @ResponseStatus(OK)
    public Kitchen update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchenParam) {
        var kitchen = this.kitchenRegistrationService.findById(kitchenId);

        BeanUtils.copyProperties(kitchenParam, kitchen, "id");
        return this.kitchenRegistrationService.save(kitchen);
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long kitchenId) {
        kitchenRegistrationService.deleteById(kitchenId);
    }
}
