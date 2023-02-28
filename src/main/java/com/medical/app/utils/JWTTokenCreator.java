package com.medical.app.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.medical.app.model.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class JWTTokenCreator {
    public enum TokenType {
        ACCESS_TOKEN, REFRESH_TOKEN
    }
    private User user = null;

    public JWTTokenCreator(User user) {
        this.user = user;
    }

    public String createToken(TokenType tokenType){
        Collection<String> rolesOfUser = new ArrayList<>();
        rolesOfUser.add(user.getRole().name());

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String returnToken = null;

        switch (tokenType){
            case ACCESS_TOKEN: {
                returnToken = JWT.create()
                        .withClaim("user_id", user.getId())
                        .withClaim("name", user.getFullName())
                        .withClaim("phone_number", user.getPhoneNumber())
                        .withClaim("avatar", user.getAvatar())
                        .withClaim("login_date", new Timestamp(System.currentTimeMillis()))
                        .withIssuedAt(new Date(System.currentTimeMillis()))
                        .withExpiresAt(new Date(System.currentTimeMillis() +100*60*1000)) //changed it to make lifetime of access token is longer
                        .withClaim("roles", new ArrayList<>(rolesOfUser))
                        .sign(algorithm);
                break;
            }

            case REFRESH_TOKEN: {
                returnToken = JWT.create()
                        .withClaim("userId", user.getId())
                        .withClaim("name", user.getFullName())
                        .withClaim("phone_number", user.getPhoneNumber())
                        .withClaim("avatar", user.getAvatar())
                        .withClaim("login_date", new Timestamp(System.currentTimeMillis()))
                        .withClaim("roles", new ArrayList<>(rolesOfUser))
                        .withIssuedAt(new Date(System.currentTimeMillis()))
                        .withExpiresAt(new Date(System.currentTimeMillis() +3000*60*1000))
                        .sign(algorithm);
                break;
            }
        }
        
        return returnToken;
    }
}
