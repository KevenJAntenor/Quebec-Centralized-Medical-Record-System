package com.santeConnect;

import com.santeConnect.web.MedicalFileController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SanteConnectApplicationTests {

    @Autowired
    private MedicalFileController controller;

    @Test
    @DisplayName("checks that the instance of the controller was created and injected successfully")
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
