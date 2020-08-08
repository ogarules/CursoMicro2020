package com.example.demo;

import java.io.IOException;

import com.example.demo.models.Pet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PetControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testSendInvalidAge() throws Exception{
        Pet pet = new Pet();
        pet.setAge(2);
        
        mvc.perform(post("/pet").contentType(MediaType.APPLICATION_JSON).content(toJson(pet)))
           .andDo(print())
           .andExpect(status().isBadRequest());
    }

    @Test
    public void testInsertOk() throws Exception{
        Pet pet = new Pet();
        pet.setAge(5);
        pet.setName("Terry");
        pet.setSpecies("golden retriever");
        pet.setEmail("oga@oga.com");
        
        mvc.perform(post("/pet").contentType(MediaType.APPLICATION_JSON).content(toJson(pet)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)));
    }

    private byte[] toJson(Object object) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}