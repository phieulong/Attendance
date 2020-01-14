package com.group.capstone.attendance.model.User.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotNull(message = "Account is required")
    @NotEmpty(message = "Account is required")
    @ApiModelProperty(
            example = "Nhatnqse04924",
            notes = "Account cannot be empty",
            required = true
    )
    private String account;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @ApiModelProperty(
            example = "sam.smith@gmail.com",
            notes = "Email cannot be empty",
            required = true
    )
    private String email;

    @NotNull(message = "First_name is required")
    @NotEmpty(message = "First_name is required")
    @ApiModelProperty(
            example = "Nhat",
            notes = "First_name cannot be empty",
            required = true
    )
    private String first_name;

    @NotNull(message = "Last_name is required")
    @NotEmpty(message = "Last_name is required")
    @ApiModelProperty(
            example = "Nguyen Quang",
            notes = "Last_name cannot be empty",
            required = true
    )
    private String last_name;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @ApiModelProperty(
            example = "Tempo123",
            notes = "Password cannot be empty",
            required = true
    )
    private String password;

    @NotNull(message = "Picture link is required")
    @NotEmpty(message = "Picture link is required")
    @ApiModelProperty(
            example = "https://i.imgur.com/OZCIFla.jpg",
            notes = "Picture link cannot be empty",
            required = true
    )
    private String picture;

    @NotNull(message = "Class_id is required")
    @ApiModelProperty(
            example = "3",
            notes = "class_id cannot be empty",
            required = true
    )
    private int class_id;

    @NotNull(message = "Role_id is required")
    @ApiModelProperty(
            example = "2",
            notes = "Role_id cannot be empty",
            required = true
    )
    private int role_id;
}
