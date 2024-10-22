package com.ryan.ninetynine.api.controller;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserForm {

    private int id;
    private String name;
    private String email;
}
