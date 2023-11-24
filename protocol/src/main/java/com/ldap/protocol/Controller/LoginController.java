package com.ldap.protocol.Controller;

import com.ldap.protocol.LoginRequest.Login;
import com.ldap.protocol.Response.AuthResponse;
import com.ldap.protocol.Response.GenericResponse;
import com.ldap.protocol.Response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LoginController {
//
//
//    AuthenticationManager authenticationManager;
//
//    public LoginController() {
//        this.authenticationManager = new ProviderManager(new ActiveDirectoryLdapAuthenticationProvider(
//                "server403.com", "ldap://34.67.239.153"));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Login loginRequest) {
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
//        try {
//            Authentication authentication = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return ResponseEntity.ok("Login successful");
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
//        }
//    }
//}


@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;

    public LoginController() {
        this.authenticationManager = new ProviderManager(new ActiveDirectoryLdapAuthenticationProvider(
                "server403.com", "ldap://34.67.239.153"));
    }

//        @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody Login loginRequest) {
//
//            UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
//        try {
//            Authentication authentication = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Fetch user data and tokens (simulated)
//            AuthResponse.UserData userData = new AuthResponse.UserData("John", "Doe", loginRequest.getUserName());
//            AuthResponse.Tokens tokens = new AuthResponse.Tokens("accessToken", "refreshToken");
//
//            // Prepare successful response
//            AuthResponse authResponse = new AuthResponse(userData, tokens, null);
//            return ResponseEntity.ok(authResponse);
//        } catch (Exception e) {
//            System.out.println(e);
//
//            AuthResponse errorResponse = new AuthResponse(null, null, "Invalid credentials");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//        }
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<?>> login(@RequestBody Login loginRequest) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Fetch user data and tokens (simulated)
            AuthResponse.UserData userData = new AuthResponse.UserData("John", "Doe", loginRequest.getUserName());
            AuthResponse.Tokens tokens = new AuthResponse.Tokens("accessToken", "refreshToken");

            // Prepare successful response
            AuthResponse authResponse = new AuthResponse(userData, tokens, null);
            ResponseData<AuthResponse> responseData = new ResponseData<>(authResponse, null);
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            System.out.println(e);

            // Prepare error response
            ResponseData<String> errorResponse = new ResponseData<>(null, "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}




