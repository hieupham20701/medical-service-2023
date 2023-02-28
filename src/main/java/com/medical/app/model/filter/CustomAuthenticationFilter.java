package com.medical.app.model.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.app.mapper.MapData;
import com.medical.app.service.AuthService;
import com.medical.app.utils.JWTTokenCreator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService){
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String phone = request.getParameter("phone_number");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(phone, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        com.medical.app.model.entity.User userResponse = MapData.mapOne(authService.getUser(user.getUsername()), com.medical.app.model.entity.User.class);

        JWTTokenCreator tokenCreator = new JWTTokenCreator(userResponse);

        try {
            String access_token = tokenCreator.createToken(JWTTokenCreator.TokenType.ACCESS_TOKEN);
            String refresh_token = tokenCreator.createToken(JWTTokenCreator.TokenType.REFRESH_TOKEN);

            Map<String,Object> tokens = new HashMap<>();
            tokens.put("name", userResponse.getFullName());
            tokens.put("user_id",userResponse.getId());
            tokens.put("phone_number",userResponse.getPhoneNumber());
            tokens.put("avatar", userResponse.getAvatar());
            tokens.put("login_date", new Date());
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            response.setContentType(APPLICATION_JSON_VALUE);

            new ObjectMapper().writeValue(response.getOutputStream(),tokens);
        }catch (Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            Map<String,String> error = new HashMap<>();
            error.put("error_message",e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),error);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setHeader("error", "Phone number or password is wrong!");
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        Map<String,String> error = new HashMap<>();
        error.put("error_message",failed.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),error);
    }
}
