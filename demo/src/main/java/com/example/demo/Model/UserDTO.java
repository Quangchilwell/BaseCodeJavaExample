package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private int age;
}
