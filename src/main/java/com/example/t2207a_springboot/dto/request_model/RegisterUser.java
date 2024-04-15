package com.example.t2207a_springboot.dto.request_model;

import lombok.Data;

@Data
public class RegisterUser {
    public String fullName;
    public String email;
    public String password;
}
