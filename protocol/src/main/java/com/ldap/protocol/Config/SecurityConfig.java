package com.ldap.protocol.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

//public interface SecurityConfig {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception;
//
//    @Bean
//    ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider();
//
//    void configure(AuthenticationManagerBuilder auth) throws Exception;
//
//}


public interface SecurityConfig {
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception;

    ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider();

    void configure(AuthenticationManagerBuilder auth) throws Exception;
}