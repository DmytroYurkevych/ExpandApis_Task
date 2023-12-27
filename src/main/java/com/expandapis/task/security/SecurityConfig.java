package com.expandapis.task.security;

import com.expandapis.task.security.filter.AuthenticationFilter;
import com.expandapis.task.security.filter.JwtAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.AUTHENTICATE_PATH);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry ->
                    registry
                            .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                            .requestMatchers(HttpMethod.POST, SecurityConstants.AUTHENTICATE_PATH).permitAll()
                            .requestMatchers(HttpMethod.POST, "/products/add").authenticated()
                            .requestMatchers(HttpMethod.GET, "/products/all").authenticated()
                            .anyRequest().authenticated()
                )
                .addFilter(authenticationFilter)
                .addFilterAfter(new JwtAuthorizationFilter(), AuthenticationFilter.class);

        return http.build();
    }

}
