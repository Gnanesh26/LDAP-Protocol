package com.ldap.protocol.Response;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthResponse {

    private UserData user;
    private Tokens tokens;
//    private String error;

    public AuthResponse(UserData user, Tokens tokens, String error) {
        this.user = user;
        this.tokens = tokens;
//        this.error = error;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public Tokens getTokens() {
        return tokens;
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }

    public static class UserData {
        private String firstname;
        private String lastname;
        private String username;

        public UserData(String firstname, String lastname, String username) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.username = username;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class Tokens {
        private String accessToken;
        private String refreshToken;

        public Tokens(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
