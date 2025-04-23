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
import zoo.web.core.application_services.dtos.request.EnclosureRequest;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EnclosureControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Создание валидного вольера")
    void createEnclosure_ValidData_Returns201() throws Exception {
        EnclosureRequest request = new EnclosureRequest("HERBIVORE", 10, 15);
        String responseJson = mockMvc.perform(post("/api/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        EnclosureResponse response = objectMapper.readValue(responseJson, EnclosureResponse.class);
        assertAll(
                () -> assertNotNull(response.enclosureId(), "id должен быть присвоен")
        );
    }

    @Test
    @DisplayName("Получение всех вольеров")
    void getEnclosures_Returns200() throws Exception {
        EnclosureRequest request = new EnclosureRequest("HERBIVORE", 10, 15);
        for (int i = 0; i < 5; i++) {
            mockMvc.perform(post("/api/enclosures")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();

        }
        String responseJson = mockMvc.perform(get("/api/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<EnclosureResponse> response = objectMapper.readValue(responseJson, List.class);
        assertAll(
                () -> assertEquals(5, response.size(), "должно быть создано 5 вольеров")
        );
    }

    @Test
    @DisplayName("Удаление вольера")
    void deleteEnclosure_Returns200() throws Exception {
        EnclosureRequest request = new EnclosureRequest("HERBIVORE", 10, 15);
        String savedId = "";
        for (int i = 0; i < 5; i++) {
            String responseJson = mockMvc.perform(post("/api/enclosures")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            if (i == 2) {
                savedId = objectMapper.readValue(responseJson, EnclosureResponse.class).enclosureId();
            }
        }
        mockMvc.perform(delete("/api/enclosures/" + savedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String responseJson = mockMvc.perform(get("/api/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<EnclosureResponse> response = objectMapper.readValue(responseJson, List.class);
        assertAll(
                () -> assertEquals(4, response.size(), "должно быть создано 4 вольера")
        );
    }
}