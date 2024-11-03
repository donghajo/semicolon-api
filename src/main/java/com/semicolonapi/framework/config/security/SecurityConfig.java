package com.semicolonapi.framework.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)     // csrf 보안 x
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(fl -> fl.disable())  // form login 사용 x
                .httpBasic(hb -> hb.disable()); // 기본 설정 사용 x

        // entry point handler
        http.exceptionHandling(conf -> conf
                .authenticationEntryPoint(new CAuthenticationEntryPoint())
                .accessDeniedHandler(new CAccessDeniedHandler())
        );
        
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                        .requestMatchers(HttpMethod.TRACE, "/**").denyAll()
                        .requestMatchers(HttpMethod.PATCH, "/**").denyAll()
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/users/**").permitAll()
    //                .requestMatchers("/colony/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/kakao/**", "/google/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/exception/**").permitAll()
                        .anyRequest().authenticated()
        );

        return http.build();
    }
}
