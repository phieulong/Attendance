package com.group.capstone.attendance.model.User.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotNull(message = "Id is required")
    @ApiModelProperty(
            example = "2",
            notes = "Id cannot be empty",
            required = true
    )
    private int id;

    @ApiModelProperty(
            example = "Nhatnqse04924",
            notes = "Account cannot be empty"
    )
    private String account;

    @Email(message = "Please provide a valid email")
    @ApiModelProperty(
            example = "sam.smith@gmail.com",
            notes = "Email cannot be empty"
    )
    private String email;

    @ApiModelProperty(
            example = "Nhat",
            notes = "First_name cannot be empty"
    )
    private String first_name;

    @ApiModelProperty(
            example = "Nguyen Quang",
            notes = "Last_name cannot be empty"
    )
    private String last_name;

    @ApiModelProperty(
            example = "Tempo123",
            notes = "Password cannot be empty"
    )
    private String password;

    @ApiModelProperty(
            example = "https://i.imgur.com/OZCIFla.jpg",
            notes = "Picture link cannot be empty"
    )
    private String picture;
}
