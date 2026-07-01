package com.itsqmet.proyecto_f.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean para encriptar contraseñas
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para manejar autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuración de seguridad y reglas de acceso
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // login y registro públicos
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN") // solo ADMIN
                        .requestMatchers("/api/clientes/**").hasAnyRole("ADMIN", "USER") // admin y user
                        .requestMatchers("/api/proyectos/**").hasAnyRole("ADMIN", "USER") // admin y user
                        .requestMatchers("/api/empleados/**").hasRole("ADMIN") // solo admin
                        .requestMatchers("/api/perfiles/**").hasRole("ADMIN") // solo admin
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.maximumSessions(1))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json");
                            response.setStatus(401);
                            response.getWriter().write(
                                    "{\"error\": \"No autenticado. Debes hacer login primero.\"}"
                            );
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setContentType("application/json");
                            response.setStatus(403);
                            response.getWriter().write(
                                    "{\"error\": \"Acceso denegado. No tienes permisos para esta acción.\"}"
                            );
                        })
                );
        return http.build();
    }
}
