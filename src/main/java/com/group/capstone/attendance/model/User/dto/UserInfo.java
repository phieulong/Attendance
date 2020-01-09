package com.group.capstone.attendance.model.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String name;
    private String class_name;
    private String email;
    private String account;
    private String avatar_link;
}
