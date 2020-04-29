package com.ffs.api.controller;

import com.ffs.api.model.Custumer;
import com.ffs.api.service.CustumerActivationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("hello")
public class HelloWordConstoller {

    private final CustumerActivationService activationService;

    public HelloWordConstoller(CustumerActivationService activationService) {
        this.activationService = activationService;
    }

    @GetMapping
    public String helloWord() {
        activationService.activate(new Custumer("João", "email@email.com", "99 99999-9999"));
        
        return "Hello Word Spring Project";
    }
}
