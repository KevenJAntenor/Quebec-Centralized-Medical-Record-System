package com.santeConnect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalFileRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthentification() throws Exception {
        // testing with correct credentials
        this.mockMvc
                .perform(post("/login")
                .content("{\"username\":\"admin\",\"password\":\"admin\"}")
                .header(HttpHeaders.CONTENT_TYPE,"application/json"))
                .andDo(print()).andExpect(status().isOk());
    }

}
