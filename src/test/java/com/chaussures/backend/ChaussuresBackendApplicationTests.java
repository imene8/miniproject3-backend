package com.chaussures.backend;

import com.chaussures.backend.entities.Chaussure;
import com.chaussures.backend.entities.ModelDeChaussure;
import com.chaussures.backend.exception.ConflictException;
import com.chaussures.backend.service.ChaussureService;
import com.chaussures.backend.service.ModelDeChaussureService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ChaussuresBackendApplicationTests {

    @Autowired
    private ChaussureService chaussureService;

    @Autowired
    private ModelDeChaussureService modelService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void seededDataAndCrudAreAvailable() {
        assertThat(modelService.getAllModeles()).hasSize(6);
        assertThat(chaussureService.getAllChaussures()).hasSize(3);
        assertThat(chaussureService.findByModele(1L)).hasSize(1);
        assertThat(chaussureService.findByNom("air")).hasSize(1);

        ModelDeChaussure modele = modelService.getModele(2L);
        Chaussure created = chaussureService.saveChaussure(new Chaussure("Test", 10.0, 39, modele));
        assertThat(created.getId()).isNotNull();

        Chaussure updated = chaussureService.updateChaussure(created.getId(),
                new Chaussure("Test modifié", 12.0, 40, modele));
        assertThat(updated.getNom()).isEqualTo("Test modifié");

        chaussureService.deleteChaussure(created.getId());
        assertThatThrownBy(() -> chaussureService.getChaussure(created.getId()))
                .hasMessageContaining("introuvable");
    }

    @Test
    void usedModelCannotBeDeleted() {
        assertThatThrownBy(() -> modelService.deleteModele(1L))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    void loginReturnsJwtAndProtectsApi() throws Exception {
        mockMvc.perform(get("/api/chaussures"))
                .andExpect(status().isUnauthorized());

        String token = login("admin", "123");

        mockMvc.perform(get("/api/chaussures")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Air Max"));
    }

    @Test
    void userRoleCanReadButCannotMutate() throws Exception {
        String userToken = login("imene", "123");
        String adminToken = login("admin", "123");
        String body = "{\"nom\":\"Test rôles\",\"prix\":10.0,\"taille\":39,\"modelDeChaussure\":{\"idModel\":1}}";

        mockMvc.perform(get("/api/chaussures")
                        .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/chaussures")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isForbidden());

        MvcResult created = mockMvc.perform(post("/api/chaussures")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        Number id = JsonPath.read(created.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(delete("/api/chaussures/" + id.longValue())
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isNoContent());
    }

    @Test
    void invalidPayloadReturnsBadRequest() throws Exception {
        String adminToken = login("admin", "123");
        String body = "{\"nom\":\"\",\"prix\":-1,\"taille\":0,\"modelDeChaussure\":null}";

        mockMvc.perform(post("/api/chaussures")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    private String login(String username, String password) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();

        return JsonPath.read(result.getResponse().getContentAsString(), "$.token");
    }
}
