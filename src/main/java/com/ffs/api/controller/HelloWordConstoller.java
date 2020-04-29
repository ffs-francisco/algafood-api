package com.ffs.api.controller;

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

    @GetMapping
    public String helloWord() {
        return "Hello Word Spring Project";
    }
}
