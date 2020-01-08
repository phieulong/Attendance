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
public class UserLoginInfoDto {
    private int id;
    private String account;
    private String password;
    private List<String> roles;
}
