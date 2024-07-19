package com.javahunter.application.config;

import com.javahunter.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;

    @Qualifier("customAuthenticationEntryPoint")
    private final AuthenticationEntryPoint authEntryPoint;

    private static final String[] GLOBAL_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/swagger-ui.html",
            "/api/v1/auth/**",
            "/api/v1/book/get/**",
            "/api/v1/book/get-all",
            "/api/v1/book/get/by/category/**",
            "/actuator/**"
    };
    private static final String[] ADMIN_WHITELIST = {
            "/api/v1/admin/**",
            "/api/v1/book/upload",
            "/api/v1/book/add",
            "/api/v1/book/update/**",
            "/api/v1/book/delete/**"
    };

    private static final String[] USER_WHITELIST = {
            "/api/v1/user/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        log.info("Inside Security Filter chain method");
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(GLOBAL_WHITELIST)
                        .permitAll()
                        .requestMatchers(ADMIN_WHITELIST).hasAnyAuthority("ADMIN")
                        .requestMatchers(USER_WHITELIST).hasAnyAuthority("USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> basic.authenticationEntryPoint(authEntryPoint))
                .exceptionHandling(Customizer.withDefaults())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
