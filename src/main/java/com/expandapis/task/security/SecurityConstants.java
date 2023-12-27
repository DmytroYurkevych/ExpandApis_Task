package com.expandapis.task.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "bVeThWmzH4t7w!z$C&F)J@NcRfUjXn2r5uaX/A?D+G-KaPdSgVkYp1^6v9y$BFE)";
    public static final int TOKEN_EXPIRATION = 3_600_000;
    public static final String JWT_PREFIX = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String REGISTER_PATH = "/user/add"; // Public path that clients can use to register.
    public static final String AUTHENTICATE_PATH = "/user/authenticate"; // Public path that clients can use to register.
}
