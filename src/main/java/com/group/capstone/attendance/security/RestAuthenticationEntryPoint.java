package com.group.capstone.attendance.security;

import com.google.gson.Gson;
import com.group.capstone.attendance.exception.BadRequest;
import com.group.capstone.attendance.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private Gson gson;
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        // Với các request xác thực không thành công, trả về mã lỗi 401
        ErrorResponse errorResponse = new ErrorResponse("You have to login",HttpStatus.BAD_REQUEST);

        String employeeJsonString = this.gson.toJson(errorResponse);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }
}