package com.example.HarareProject.config;

import com.example.HarareProject.Entity.Permissions;
import com.example.HarareProject.Entity.Role;
import com.example.HarareProject.filters.JWTAuthFilter;
import com.example.HarareProject.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {

    @Autowired
    JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                //csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/authenticate").permitAll()
                                        .requestMatchers("/api/users/register").permitAll()
                                        .requestMatchers(HttpMethod.GET).permitAll()
                                        .requestMatchers(HttpMethod.POST).permitAll()
                                        .requestMatchers(HttpMethod.DELETE).permitAll()
                                        .requestMatchers(HttpMethod.PUT).permitAll()
                                        //.requestMatchers("/api/skill").permitAll()
                                        //.requestMatchers("/ticket/").hasRole("ADMIN")
                                        //.requestMatchers("/ticket/").hasRole(Role.ADMIN.name())
                                        // .requestMatchers(HttpMethod.GET,"/ticket/**").hasAuthority(Permissions.TIK_READ.name())
                                        // .requestMatchers(HttpMethod.POST,"/ticket/**").hasAuthority(Permissions.TIK_WRITE.name())
                                        .anyRequest().authenticated());
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
