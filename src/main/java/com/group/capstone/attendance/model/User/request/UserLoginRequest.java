package com.group.capstone.attendance.model.User.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    @NotNull(message = "account is required")
    @NotEmpty(message = "account is required")
    @ApiModelProperty(
            example = "Nhatnqse04924",
            notes = "Account cannot be empty",
            required = true
    )
    private String account;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @ApiModelProperty(
            example = "12345678",
            notes = "Password cannot be empty",
            required = true
    )
    private String password;
}
