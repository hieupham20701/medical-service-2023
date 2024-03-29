package com.medical.app.security;

import com.medical.app.model.filter.CustomAuthenticationFilter;
import com.medical.app.model.filter.CustomAuthorizationFilter;
import com.medical.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthService authService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), authService);
        customAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(POST ,"/api/auth/login/**","/api/auth/register/**","/api/auth/forgot/password/**","/api/auth/password/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/auth/exist/**","/api/auth/hello/**").permitAll();
        http.authorizeRequests().antMatchers(POST ,"/api/auth/refresh_token/**" ).permitAll();

        http.authorizeRequests().antMatchers(GET ,"/api/auth/refresh/**" ).permitAll();
//        http.authorizeRequests().antMatchers("/api/patients/**","/api/services/**","/api/drugs/**","/api/detail_batch_drugs/**","/api/medical_letters/**","/api/medical_examinations/**","/api/medical_detail_examinations/**","/api/medicines/**","/api/suppliers/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/api/patients/**","/api/services/**","/api/drugs/**","/api/detail_batch_drugs/**","/api/medical_letters/**","/api/medical_examinations/**","/api/medical_detail_examinations/**","/api/medicines/**","/api/suppliers/**","/api/users/**","/api/category_drugs/**","/api/medical_departments/**","/api/rooms/**","/api/batch_drugs/**","/api/category_services/**","/api/statistics/**").permitAll();

        http.authorizeRequests().antMatchers(GET ,"/api/auth/roles/**" ).hasAnyAuthority("ADMIN");

//        http.authorizeRequests().antMatchers("/api/batch_drugs/**").hasAnyAuthority("NEWMEM");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Set-Cookie", "Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5173","http://localhost:5173","http://localhost:3000","http://localhost:3001",
                "https://fe-tindi-n13-ts-redux-kv4a.vercel.app","exp://192.168.1.2:19000","https://web-introduce-doantotnghiep-git-master-parzival1405.vercel.app/")); // evn
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
        return source;
    }

}
