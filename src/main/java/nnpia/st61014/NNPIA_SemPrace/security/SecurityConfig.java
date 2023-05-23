package nnpia.st61014.NNPIA_SemPrace.security;

import lombok.RequiredArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.service.AppUserService;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private JwtAuthEntryPoint authEntryPoint;
    private AppUserService userDetailsService;

    @Autowired
    public SecurityConfig(AppUserService userDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/job-listing/all")
                        .permitAll()
                        .anyRequest().authenticated()
                ).formLogin().disable()
                // .httpBasic(Customizer.withDefaults());
                .httpBasic(httpBasicConfigurer_ -> {
                    httpBasicConfigurer_.authenticationEntryPoint(authEntryPoint);
                });
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource corsConfSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConf = new CorsConfiguration();
        corsConf.setAllowCredentials(true);
        corsConf.addAllowedOrigin("http://127.0.0.1:5173");
        corsConf.addAllowedOrigin("http://localhost:5173");
        corsConf.addAllowedHeader("*");
        corsConf.addAllowedMethod("*");
        corsConfSource.registerCorsConfiguration("/**", corsConf);
        return new CorsFilter(corsConfSource);
    }
}
