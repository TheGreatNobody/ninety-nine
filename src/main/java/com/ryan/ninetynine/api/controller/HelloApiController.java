package com.ryan.ninetynine.api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HelloApiController {

    @GetMapping("hello")
    public String sayHello(@Parameter String name) {
        return "Hello {name} World!";
    }

    @GetMapping("hello/{name}")
    public String sayHelloByPath(@PathVariable String name) {
        return "Hello {name} World!";
    }

}
