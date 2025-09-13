package com.example.HarareProject.config;

import com.example.HarareProject.Entity.Permissions;
import com.example.HarareProject.Entity.Role;
import com.example.HarareProject.filters.JWTAuthFilter;
import com.example.HarareProject.Service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {

    @Autowired
    JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.setAllowedOriginPatterns(List.of(
                            "https://harareproject-production.up.railway.app",
                            "http://harareproject-production.up.railway.app",
                            "http://localhost:*"
                    ));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/authenticate").permitAll()
                                        .requestMatchers("/api/users/register").permitAll()
                                        .requestMatchers(HttpMethod.GET).permitAll()
                                        .requestMatchers(
                                                "/v3/api-docs/**",
                                                "/swagger-ui/**",
                                                "/swagger-ui.html"
                                        ).permitAll()
                                        //.requestMatchers(HttpMethod.POST).permitAll()
                                        //.requestMatchers(HttpMethod.DELETE).permitAll()
                                        //.requestMatchers(HttpMethod.PUT).permitAll()
                                        .anyRequest().authenticated())
                                        .oauth2Login(Customizer.withDefaults())
                                           // ✅ Handle API calls differently
                                        .exceptionHandling(ex -> ex
                                        .authenticationEntryPoint((request,
                                                                   response,
                                                                   authException) -> {
                                        String uri = request.getRequestURI();
                                    if (uri.startsWith("/api/") || uri.startsWith("/authenticate")) {
                                        // API request → return JSON
                                response.setContentType("application/json");
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.getWriter().write("{\"error\": \"Unauthorized or invalid token\"}");
                            } else {
                                // Browser request → default behavior (redirect to login)
                                response.sendRedirect("/login");
                            }
                        })
                );
        //.httpBasic(withDefaults());
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(dao);

    }

}