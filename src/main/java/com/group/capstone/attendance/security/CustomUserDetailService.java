package com.group.capstone.attendance.security;

import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import com.group.capstone.attendance.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserLoginInfoDto userLoginInfoDto = userService.getUserLoginInfoByAccount(account);
        if (userLoginInfoDto != null) {
            Set<GrantedAuthority> roles = new HashSet<>();
            for(String u : userLoginInfoDto.getRoles()){
                roles.add(new SimpleGrantedAuthority(u));
            }
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles);
            // Lưu ý: Password ở đây đã được hash sử dụng BCrypt từ lúc tạo user
            // Trả về thông tin để kiểm tra xác thực: username, password, quyền
            return new org.springframework.security.core.userdetails.User(account, userLoginInfoDto.getPassword(), authorities);
        } else {
            throw new RecordNotFoundException("user get email " + account + " does not exist.");
        }
    }
}
