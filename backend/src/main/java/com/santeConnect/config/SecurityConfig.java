package com.santeConnect.config;

import com.santeConnect.AuthEntryPoint;
import com.santeConnect.AuthentificationFilter;
import com.santeConnect.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthentificationFilter authentificationFilter;
    private final AuthEntryPoint exceptionHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthentificationFilter authentificationFilter,
            AuthEntryPoint exceptionHandler) {
        this.userDetailsService = userDetailsService;
        this.authentificationFilter = authentificationFilter;
        this.exceptionHandler = exceptionHandler;
    }

    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authentificationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.csrf((csrf) -> csrf.disable())
         .cors(withDefaults())
         .sessionManagement((sessionManagement) -> sessionManagement.
         sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests((authorizeHttpRequests) ->
         authorizeHttpRequests
                 // TODO delete line below when login is implemented
                 .requestMatchers("medical-files/**").permitAll()
                 .requestMatchers("/api-docs/**", "/swagger-ui/**").permitAll()
                 .requestMatchers(HttpMethod.POST, "/login").permitAll()
                 // TODO define roles endpoint
//                 .requestMatchers("/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/user/**").hasRole("USER")
                 .anyRequest().authenticated())
         .addFilterBefore(authentificationFilter,
         UsernamePasswordAuthenticationFilter.class)
         .exceptionHandling((exceptionHandling) -> exceptionHandling.
         authenticationEntryPoint(exceptionHandler));
         
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        // localhost:3000 is allowed, an example
        // config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        ;
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
