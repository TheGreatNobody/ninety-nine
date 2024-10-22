package com.ryan.ninetynine.api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("hello")
    public UserForm sayHello(@RequestBody UserBean userBean) {
        return UserForm.builder()
                .name(userBean.getName())
                .id(userBean.getId())
                .email("ryan@gamil.com").build();
    }


}
