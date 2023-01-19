package com.softserve.itacademy.security;

import com.softserve.itacademy.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private WebAccessDeniedHandler webAccessDeniedHandler;

    static final String LOGIN_PAGE = "/form-login";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users/create", LOGIN_PAGE).permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .loginProcessingUrl(LOGIN_PAGE)
                .failureUrl(LOGIN_PAGE + "?error")
                .defaultSuccessUrl("/home")
                .permitAll()
                    .and()
                .logout()
                //.logoutUrl("/perform-logout")
                //.logoutSuccessUrl(LOGIN_PAGE + "?logout")
                .deleteCookies("JSESSIONID")
                    .and()
                            .csrf().disable()
                .userDetailsService(userDetailsService);

        http.exceptionHandling().accessDeniedHandler(webAccessDeniedHandler);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }
}
