package zoo.web.adapters.controllers;

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
import zoo.web.core.application_services.dtos.request.FeedingTimeRequest;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;
import zoo.web.core.application_services.dtos.response.FeedingTimeResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AnimalFeedingControllerTest {
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

    private String createAnimal(String animalEatType) throws Exception {
        String enclosureId = createEnclosure(animalEatType);
        AnimalRequest request = new AnimalRequest(animalEatType, "dog", "MALE", "bone", "01.04.2004", "true");
        String responseJson = mockMvc.perform(post("/api/animals/"+enclosureId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        AnimalResponse response = objectMapper.readValue(responseJson, AnimalResponse.class);
        return response.AnimalId();
    }

    @Test
    @DisplayName("Создание валидного кормления")
    void createFeedingSchedule_ValidData_Returns201() throws Exception {
        String animalId = createAnimal("HERBIVORE");
        FeedingTimeRequest request = new FeedingTimeRequest(animalId, "20.04.2025 10:00", 2, "bone");
        String responseJson = mockMvc.perform(post("/api/animals/feed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        FeedingTimeResponse response = objectMapper.readValue(responseJson, FeedingTimeResponse.class);
        assertAll(
                () -> assertNotNull(response.feedingTimeId(), "id должен быть присвоен")
        );
    }

    @Test
    @DisplayName("Запуск валидного кормления")
    void runFeedingSchedule_ValidData_Returns201() throws Exception {
        String animalId = createAnimal("HERBIVORE");
        FeedingTimeRequest request = new FeedingTimeRequest(animalId, "20.04.2025 10:00", 2, "bone");
        mockMvc.perform(post("/api/animals/feed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(post("/api/animals/feed/run"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @DisplayName("Получение всех кормлений")
    void getFeedingSchedule_ValidData_Returns201() throws Exception {
        String animalId = createAnimal("HERBIVORE");
        FeedingTimeRequest request = new FeedingTimeRequest(animalId, "20.04.2025 10:00", 2, "bone");
        for (int i = 0; i < 5; i++) {
            mockMvc.perform(post("/api/animals/feed")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
        }

        String responseJson = mockMvc.perform(get("/api/animals/feed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<FeedingTimeResponse> response = objectMapper.readValue(responseJson, List.class);
        assertAll(
                () -> assertEquals(5, response.size(), "id должен быть присвоен 5 раз")
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