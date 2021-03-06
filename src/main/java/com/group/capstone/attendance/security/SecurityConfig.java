package com.group.capstone.attendance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

        // Cấu hình user mặc định để thực hiện intergration test
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("spring")
                .password(passwordEncoder().encode("secret"))
                .roles("USER");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Có thể chỉ định rõ: https://techmaster.vn
        config.addAllowedHeader("*"); // Có thể chỉ định rõ: Arrays.asList("authorization", "content-type", "x-auth-token")
        config.addAllowedMethod("*"); // Có thể chỉ định rõ: Arrays.asList("GET", "POST", "PUT", "DELETE")
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // Lọc CORS -> corsFilter()
                .and()
                .csrf() // Trong Spring , chế độ bảo vệ khỏi CSRF mặc định được bật -> không cần thì disable đi
                .disable()
                .authorizeRequests()
                .antMatchers("/api/student/schedule/").hasAnyRole("STUDENT")
                .antMatchers("/api/student/attendance/{schedule_id}").hasAnyRole("STUDENT")
                .antMatchers("/api/student/info").hasAnyRole("STUDENT")
                .antMatchers("/api/teacher/schedule/").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/info/").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/attendance/").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/getAllTeacherInfo").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/getAllClassInfo").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/getAllSubjectInfo").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/getAllRoomInfo").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/getAllTermInfo").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/getAllCategoryInfo").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/createSchedule").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/createUser").hasAnyRole("TEACHER")
                .antMatchers("/api/teacher/take-attendance/{id}").hasAnyRole("TEACHER")
                .antMatchers("/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()//xu ly exception
                .authenticationEntryPoint(restAuthenticationEntryPoint)//class xu ly exception
                .and()
                .addFilter(new ApiJWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Vô hiệu hóa toàn bộ bảo mật đối với các request vào các đường dẫn sau
        // Không phải trải qua filter
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/api/student/schedule/{id}",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }
}
