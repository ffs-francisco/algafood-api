package com.ffs.algafood.api.controller;

import com.ffs.algafood.api.model.request.kitchen.KitchenRequest;
import com.ffs.algafood.api.model.response.kitchen.KitchenResponse;
import com.ffs.algafood.domain.service.KitchenService;
import java.util.List;
import javax.validation.Valid;
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
    private KitchenService kitchenService;

    @GetMapping
    @ResponseStatus(OK)
    public List<KitchenResponse> listAll() {
        return KitchenResponse.fromList(this.kitchenService.findAll());
    }

    @GetMapping("/{kitchenId}")
    @ResponseStatus(OK)
    public KitchenResponse findById(@PathVariable Long kitchenId) {
        return KitchenResponse.from(kitchenService.findById(kitchenId));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public KitchenResponse add(@RequestBody @Valid final KitchenRequest kitchen) {
        return KitchenResponse.from(this.kitchenService.save(kitchen.toModel()));
    }

    @PutMapping("/{kitchenId}")
    @ResponseStatus(OK)
    public KitchenResponse update(@PathVariable final Long kitchenId, @RequestBody @Valid final KitchenRequest kitchenRequest) {
        var kitchen = this.kitchenService.findById(kitchenId);

        kitchenRequest.copyPropertiesTo(kitchen);
        return KitchenResponse.from(this.kitchenService.save(kitchen));
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long kitchenId) {
        kitchenService.deleteById(kitchenId);
    }
}
