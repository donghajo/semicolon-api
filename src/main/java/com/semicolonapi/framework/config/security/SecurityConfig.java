package com.semicolonapi.framework.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable(); // form login 사용 x
        http.httpBasic().disable(); // 기본 설정 사용 x
        http.csrf().disable();  // csrf 보안 x

        http.exceptionHandling()
            .accessDeniedHandler(new CAccessDeniedHandler())
            .authenticationEntryPoint(new CAuthenticationEntryPoint());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 x

        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                .antMatchers(HttpMethod.TRACE, "/**").denyAll()
                .antMatchers(HttpMethod.PATCH, "/**").denyAll()
                .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/api/users/**").permitAll()
//                .antMatchers("/colony/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/").permitAll()
                .antMatchers("/kakao/**", "/google/**").permitAll()
                .antMatchers(HttpMethod.GET, "/exception/**").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
