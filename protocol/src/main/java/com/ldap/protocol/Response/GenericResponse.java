package com.ldap.protocol.Response;

public class GenericResponse {
    private AuthResponse authResponse;
    private ErrorResponse errorResponse;

    public GenericResponse(AuthResponse authResponse, ErrorResponse errorResponse) {
        this.authResponse = authResponse;
        this.errorResponse = errorResponse;
    }

    public AuthResponse getAuthResponse() {
        return authResponse;
    }

    public void setAuthResponse(AuthResponse authResponse) {
        this.authResponse = authResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
