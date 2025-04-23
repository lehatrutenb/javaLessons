package zoo.web.adapters.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import zoo.web.core.application_services.dtos.request.AnimalRequest;
import zoo.web.core.application_services.dtos.request.EnclosureRequest;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private String createEnclosure(String animalEatType) throws Exception {
        EnclosureRequest request = new EnclosureRequest(animalEatType, 10, 15);
        String responseJson = mockMvc.perform(post("/api/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        EnclosureResponse response = objectMapper.readValue(responseJson, EnclosureResponse.class);
        return response.enclosureId();
    }

    @Test
    @DisplayName("Создание валидного животного")
    void createAnimal_ValidData_Returns201() throws Exception {
        String enclosureId = createEnclosure("HERBIVORE");
        AnimalRequest request = new AnimalRequest("HERBIVORE", "dog", "MALE", "bone", "01.04.2004", "true");
        String responseJson = mockMvc.perform(post("/api/animals/"+enclosureId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        AnimalResponse response = objectMapper.readValue(responseJson, AnimalResponse.class);
        assertAll(
                () -> assertEquals(response.EnclosureId(), enclosureId, "id клетки не должен поменяться"),
                () -> assertNotNull(response.AnimalId(), "id должен быть присвоен")
        );
    }

    @Test
    @DisplayName("Создание валидного животного в како-нибудь вольер")
    void createAnimalToAny_ValidData_Returns201() throws Exception {
        String enclosureId = createEnclosure("HERBIVORE");
        AnimalRequest request = new AnimalRequest("HERBIVORE", "dog", "MALE", "bone", "01.04.2004", "true");
        String responseJson = mockMvc.perform(post("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        AnimalResponse response = objectMapper.readValue(responseJson, AnimalResponse.class);
        assertAll(
                () -> assertNotNull(response.EnclosureId(), "id клетки должен быть присвоен"),
                () -> assertNotNull(response.AnimalId(), "id должен быть присвоен")
        );
    }

    @Test
    @DisplayName("Move валидного животного")
    void moveAnimal_ValidData_Returns201() throws Exception {
        String enclosureId = createEnclosure("HERBIVORE");
        String enclosureId2 = createEnclosure("HERBIVORE");
        AnimalRequest request = new AnimalRequest("HERBIVORE", "dog", "MALE", "bone", "01.04.2004", "true");
        String responseJson = mockMvc.perform(post("/api/animals/"+enclosureId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        AnimalResponse response = objectMapper.readValue(responseJson, AnimalResponse.class);
        String animalId = response.AnimalId();

        mockMvc.perform(patch("/api/animals/"+animalId+"/"+enclosureId2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }


    @Test
    @DisplayName("Получение всех животных")
    void getAnimals_ValidData_Returns200() throws Exception {
        String enclosureId = createEnclosure("HERBIVORE");
        for (int i = 0; i < 5; i++) {
            AnimalRequest request = new AnimalRequest("HERBIVORE", "dog", "MALE", "bone", "01.04.2004", "true");
            mockMvc.perform(post("/api/animals/" + enclosureId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
        }

        String responseJson = mockMvc.perform(get("/api/animals"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<AnimalResponse> response = objectMapper.readValue(responseJson, List.class);
        assertAll(
                () -> assertEquals(5, response.size(), "должно создастся 5 жив в 1 вольере")
        );
    }

    @Test
    @DisplayName("Удаление животного")
    void deleteAnimal_ValidData_Returns200() throws Exception {
        String enclosureId = createEnclosure("HERBIVORE");
        String animalId = "";
        for (int i = 0; i < 5; i++) {
            AnimalRequest request = new AnimalRequest("HERBIVORE", "dog", "MALE", "bone", "01.04.2004", "true");
            String responseJson = mockMvc.perform(post("/api/animals/" + enclosureId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            if (i == 2) {
                AnimalResponse animalResponse = objectMapper.readValue(responseJson, AnimalResponse.class);
                animalId = animalResponse.AnimalId();
            }
        }

        mockMvc.perform(delete("/api/animals/"+animalId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String responseJson = mockMvc.perform(get("/api/animals"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<AnimalResponse> response = objectMapper.readValue(responseJson, List.class);
        assertAll(
                () -> assertEquals(4, response.size(), "должно создастся 4 жив в 1 вольере")
        );
    }
}