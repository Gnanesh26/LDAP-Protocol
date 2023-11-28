package com.ldap.protocol.Controller;

import com.ldap.protocol.Config.JwtTokenProvider;
import com.ldap.protocol.LoginRequest.Login;
import com.ldap.protocol.Response.AuthResponse;
import com.ldap.protocol.Response.ResponseData;
import com.ldap.protocol.Service.LdapService;
import org.hibernate.boot.model.source.spi.SingularAttributeSourceToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    AuthenticationManager authenticationManager;

    public LoginController() {
        this.authenticationManager = new ProviderManager(new ActiveDirectoryLdapAuthenticationProvider(
                "server403.com", "ldap://34.67.239.153"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login loginRequest) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            System.out.println(authentication);
            LdapUserDetails ldapUserDetails= (LdapUserDetails) authentication.getPrincipal();
            System.out.println(ldapUserDetails);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }
    }
}

//
//@RestController
//public class LoginController {
//
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private LdapService ldapService;
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
////
////    public LoginController() {
////        this.authenticationManager = new ProviderManager(new ActiveDirectoryLdapAuthenticationProvider(
////                "server403.com", "ldap://34.67.239.153"));
////    }
//
//    //    @Autowired
//    public LoginController(LdapService ldapService, JwtTokenProvider jwtTokenProvider) {
//        this.ldapService = ldapService;
//        this.authenticationManager = new ProviderManager(new ActiveDirectoryLdapAuthenticationProvider(
//                "server403.com", "ldap://34.67.239.153"));
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<ResponseData<?>> login(@RequestBody Login loginRequest) {
//        try {
//            // Authenticate user (assuming ldapService is a functional LDAP service)
//            if (ldapService.authenticate(loginRequest.getUserName(), loginRequest.getPassword())) {
//                // Fetch user data from LDAP
//                String firstName = ldapService.getFirstName(loginRequest.getUserName());
//                String lastName = ldapService.getLastName(loginRequest.getUserName());
//
//                // Generate JWT tokens
//                String accessToken = jwtTokenProvider.createAccessToken(loginRequest.getUserName());
//                String refreshToken = jwtTokenProvider.createRefreshToken(loginRequest.getUserName());
//
//                // Prepare successful response
//                AuthResponse.UserData userData = new AuthResponse.UserData(firstName, lastName, loginRequest.getUserName());
//                AuthResponse.Tokens tokens = new AuthResponse.Tokens(accessToken, refreshToken);
//                AuthResponse authResponse = new AuthResponse(userData, tokens, null);
//
//                ResponseData<AuthResponse> responseData = new ResponseData<>(authResponse, null);
//                return ResponseEntity.ok(responseData);
//            } else {
//                // Prepare error response for invalid credentials
//                ResponseData<String> errorResponse = new ResponseData<>(null, "Invalid credentials");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            // Prepare error response for unexpected errors
//            ResponseData<String> errorResponse = new ResponseData<>(null, "An error occurred");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }
//}



