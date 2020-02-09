package com.example.demo.configurations;

import com.example.demo.domain.ApplicationRolePermissions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.domain.ApplicationUserRoles.ADMIN;
import static com.example.demo.domain.ApplicationUserRoles.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
//                .hasAuthority(ApplicationRolePermissions.STUDENTS_READ.getPermission())
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails annasmith =
                User
                        .builder()
                        .username("annasmith")
                        .password(passwordEncoder().encode("pass123"))
                        .authorities(ADMIN.getGrantedAuthorities())
                        .build();

        return new InMemoryUserDetailsManager(annasmith);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
