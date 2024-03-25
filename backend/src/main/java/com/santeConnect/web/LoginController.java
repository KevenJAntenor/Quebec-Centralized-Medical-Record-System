package com.santeConnect.web;

import com.santeConnect.domain.jwt.AccountCredentials;
import com.santeConnect.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        // generate token nd send it in the response auth header
        UsernamePasswordAuthenticationToken creds = new
                UsernamePasswordAuthenticationToken(credentials.username(),
                                                    credentials.password());
        Authentication auth = authenticationManager.authenticate(creds);

        // Generate Token
        String jwts = jwtService.getToken(auth.getName());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,
                "Bearer" + jwts).header(
                        HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                "Authorization").build();
    }
}
