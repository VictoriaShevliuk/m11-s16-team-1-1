package com.softserve.itacademy.security;

import com.softserve.itacademy.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    static final String LOGIN_PAGE = "/form-login";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .loginProcessingUrl(LOGIN_PAGE)
                .failureUrl(LOGIN_PAGE + "?error")
                //.failureUrl(LOGIN_PAGE)
                .defaultSuccessUrl("/home")
                .permitAll()
                    .and()
                .logout()
                //.logoutUrl("/perform-logout")
                //.logoutUrl(LOGIN_PAGE + "?logout=true")
                //.logoutSuccessUrl(LOGIN_PAGE + "(logout=true)")
                //.logoutSuccessUrl(LOGIN_PAGE + "?logout")
                .deleteCookies("JSESSIONID")
                    .and()
                            .csrf().disable()
                .userDetailsService(userDetailsService)
                ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

//        auth.inMemoryAuthentication()
//                .withUser("mike@mail.com")
//                .password("{noop}123")
//                .roles("ADMIN");

    }
}
