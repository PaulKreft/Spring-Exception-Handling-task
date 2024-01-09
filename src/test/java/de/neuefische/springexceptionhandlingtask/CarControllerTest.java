package de.neuefische.springexceptionhandlingtask;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCarBrandTest_whenPathVariableIsAudi_throwIllegalArgumentException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/audi"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertInstanceOf(IllegalArgumentException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Only 'porsche' allowed", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void getAllCars_whenGetAllCars_throwNoSuchElementException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertInstanceOf(NoSuchElementException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("No Cars found", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void getNullPointerException_whenCalled_throwNullPointerException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/null"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertInstanceOf(NullPointerException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Cannot invoke \"String.charAt(int)\" because \"nullString\" is null", Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andReturn();

        assertEquals(new JSONObject(mvcResult.getResponse().getContentAsString()).getString("message"), "Standard exception: Cannot invoke \"String.charAt(int)\" because \"nullString\" is null");

    }

    @Test
    void getArithmeticException_whenCalled_throwGetNumberOfCars() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/number"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertInstanceOf(ArithmeticException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("/ by zero", Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andReturn();

        assertEquals(new JSONObject(mvcResult.getResponse().getContentAsString()).getString("message"), "Standard exception: / by zero");

    }

}