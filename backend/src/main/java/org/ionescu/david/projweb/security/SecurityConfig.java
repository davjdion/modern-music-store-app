package org.ionescu.david.projweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions().disable())
                .cors()
                .and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console").permitAll()
                        .requestMatchers("/api/users/auth/login").permitAll()
                        .requestMatchers("/api/users/**").authenticated()
                        .requestMatchers("/api/cart/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.defaultSuccessUrl("/"))
                .httpBasic(customizer -> customizer.realmName("ProjWeb"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT email AS username, password, true AS enabled FROM \"USER\" WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT email AS username, 'ROLE_USER' AS authority FROM \"USER\" WHERE email = ?"); // Assign a default role
        return userDetailsManager;
    }
}
