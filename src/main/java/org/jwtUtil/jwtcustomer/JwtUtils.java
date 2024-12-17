package org.jwtUtil.jwtcustomer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtils {

    public static String extractCustomerIdFromToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("JWT Token is missing or invalid");
        }

        String token = authorizationHeader.substring(7);
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("customerId").asString();
    }
}