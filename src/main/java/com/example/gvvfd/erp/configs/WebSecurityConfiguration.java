package com.example.gvvfd.erp.configs;

import com.example.gvvfd.erp.Models.enums.UserRole;
import com.example.gvvfd.erp.Services.JWT.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final JwtAuthenticationFilter filter;
    private final UserService svc;

    @Bean
    public SecurityFilterChain secFChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
                        .permitAll()
                        .requestMatchers("/api/command/**")
                        .hasAnyAuthority(UserRole.Command.name())
                        .requestMatchers("/api/officer/**")
                        .hasAnyAuthority(UserRole.Officer.name())
                        .requestMatchers("/api/member/**")
                        .hasAnyAuthority(UserRole.Member.name())
                        .anyRequest().authenticated())
                .sessionManagement(mgr -> mgr.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager am(AuthenticationConfiguration configs) throws Exception {
        return configs.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider aP = new DaoAuthenticationProvider();
        aP.setUserDetailsService(svc.UDS());
        aP.setPasswordEncoder(passwordEncoder());
        return aP;
    }
}
