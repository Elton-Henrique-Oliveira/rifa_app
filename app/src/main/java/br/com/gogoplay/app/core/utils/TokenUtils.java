package br.com.gogoplay.app.core.utils;

import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    public static Map<String, String> extractTokenAndId(String authorizationHeader) {
        Map<String, String> tokenInfo = new HashMap<>();

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Obtém o token de autenticação removendo "Bearer " do início
            String fullToken = authorizationHeader.substring(7);

            // Divide o token usando o ponto e vírgula como delimitador
            String[] tokenParts = fullToken.split(",");

            // Adiciona a parte "Bearer" ao mapa
            tokenInfo.put("Bearer", tokenParts[0].trim());

            // Procura pela parte que contém "Id"
            for (String part : tokenParts) {
                if (part.trim().startsWith("Id ")) {
                    String[] idParts = part.trim().split(" ");
                    if (idParts.length == 2) {
                        tokenInfo.put("Id", idParts[1].trim());
                    }
                    break; // Paramos assim que encontramos a parte com "Id"
                }
            }
        }

        return tokenInfo;
    }
}

