package com.softserve.itacademy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    static final String LOGIN_PAGE = "/form-login";


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
                .logoutUrl("/perform-logout")
                //.logoutUrl(LOGIN_PAGE + "?logout=true")
                .logoutSuccessUrl(LOGIN_PAGE + "(logout=true)")
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)

        auth.inMemoryAuthentication()
                .withUser("mike@mail.com")
                .password("{noop}123")
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser("nick@mail.com")
                .password("{noop}123")
                .roles("USER");



    }
}
