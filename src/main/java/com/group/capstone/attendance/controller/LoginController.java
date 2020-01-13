package com.group.capstone.attendance.controller;

import com.group.capstone.attendance.model.User.dto.UserLoginInfoDetailDto;
import com.group.capstone.attendance.model.User.request.UserLoginRequest;
import com.group.capstone.attendance.service.User.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/login")
@Api(value = "Login Apis")
public class LoginController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="Login api", response = UserLoginInfoDetailDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Da ton tai user nay"),
            @ApiResponse(code = 500, message="Server bi loi"),
    })
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserLoginRequest userLoginRequest){
        UserLoginInfoDetailDto userLoginInfoDetailDto =
                userService.login(userLoginRequest.getAccount(), userLoginRequest.getPassword());
        return ResponseEntity.ok(userLoginInfoDetailDto);
    }
}
