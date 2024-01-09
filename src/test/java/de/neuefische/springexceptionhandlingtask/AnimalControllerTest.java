package de.neuefische.springexceptionhandlingtask;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAnimalSpeciesTest_whenPathVariableIsCat_throwIllegalArgumentException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertInstanceOf(IllegalArgumentException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Only 'dog' is allowed", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void getAllAnimals_whenGetAllAnimals_throwNoSuchElementException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertInstanceOf(NoSuchElementException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("No Animals found", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

}