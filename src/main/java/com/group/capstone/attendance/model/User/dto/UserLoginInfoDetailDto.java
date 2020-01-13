package com.group.capstone.attendance.model.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInfoDetailDto {
    private int id;
    private String name;
    private String class_name;
    private String email;
    private String account;
    private String avatar_link;
    private String token;
    private List<String> roles;
}
