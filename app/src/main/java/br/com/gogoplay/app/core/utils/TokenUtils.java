package br.com.gogoplay.app.core.utils;

public class TokenUtils {

    public static String extractToken(String authorizationHeader) {
        String token = "";

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        return token;
    }
}

