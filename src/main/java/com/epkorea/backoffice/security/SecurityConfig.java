package com.epkorea.backoffice.security;

import com.epkorea.backoffice.entity.RoleEnum;
import com.epkorea.backoffice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final AuthorityDeniedHandler authorityDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/swagger-ui/", "/user/all", "/user/login").permitAll()
                .mvcMatchers("/user/logs", "/user/signup").hasRole(RoleEnum.ADMIN.name())
                .mvcMatchers("/social/**").hasRole(RoleEnum.SOCIAL.name())
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/user/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler);
        http.logout()
                .logoutSuccessUrl("/user/all");
        http.httpBasic();
        http.csrf().disable();
        http.exceptionHandling().accessDeniedHandler(authorityDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/image/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
