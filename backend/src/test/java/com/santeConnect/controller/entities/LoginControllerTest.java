package com.santeConnect.web;

import com.santeConnect.domain.jwt.AccountCredentials;
import com.santeConnect.repository.users.AppUserRepository;

import com.santeConnect.repository.entities.CoordinateRepository;
import com.santeConnect.repository.entities.MedicalFileRepository;
import com.santeConnect.repository.entities.MedicalVisitRepository;
import com.santeConnect.repository.entities.PatientRepository;
import com.santeConnect.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable security filter
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserDetailsService userDetailsService; // Mocked UserDetailsService

    @MockBean
    private PasswordEncoder passwordEncoder; // Mocked PasswordEncoder

    @MockBean
    private MedicalFileRepository medicalFileRepository;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private CoordinateRepository coordinateRepository;

    @MockBean
    private MedicalVisitRepository medicalVisitRepository;

    @MockBean
    private AppUserRepository appUserRepository;

    @Test
    void testLogin() throws Exception {
        AccountCredentials credentials = new AccountCredentials("user", "password");

        Authentication auth = new UsernamePasswordAuthenticationToken("user", null);

        given(authenticationManager.authenticate(any())).willReturn(auth);
        given(jwtService.getToken("user")).willReturn("fakeToken");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", "BearerfakeToken"));
    }
}

