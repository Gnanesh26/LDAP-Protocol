package com.ldap.protocol.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/ldap")
public class ProtocolController {

    @GetMapping("/security")
    public String securityMethod() {
        return "Welcome To LDAP!!";
    }


    @GetMapping("/hello")
    public String sayHello(Authentication authentication) {
        System.out.println(authentication.toString());
        return "hello world";
    }

    @GetMapping("/user")
    public Authentication getLoggedUserDeatil() {

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //get username
        String username = auth.getName();
// concat list of authorities to single string seperated by comma
        String authorityString = auth
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
// check if the user have authority -roleA
        String role = "role_A";
        boolean isCurrentUserInRole = auth
                .getAuthorities()
                .stream()
                .anyMatch(role::equals);
//return Authentication object
        return auth;
    }
}


