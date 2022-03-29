package es.castellor.testeate.questions;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PackIntegrationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void get_list_of_packs() throws Exception {
        mockMvc.perform(get("/pack"))
                .andExpect(status().isOk());
    }

    @Test
    void get_list_of_active_packs() throws Exception {
        mockMvc.perform(get("/pack/active"))
                .andExpect((status().isOk()));

    }

    @Test
    void createPack_with_empty_body() throws Exception {
        mockMvc.perform(post("/pack"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createPack_with_invalid_uuid() throws Exception {
        mockMvc.perform(post("/pack")
                        .content("{\"uuid\":\"aaaa\", \"name\":\"\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createPack_with_valid_uuid() throws Exception {
        mockMvc.perform(post("/pack")
                        .content("{\"uuid\":\"fbf1a956-afa6-11ec-9ff3-a786d2f954c4\", \"name\":\"Name 1\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void create_pack_with_valid_uuid_and_fail_on_same_uuid() throws Exception {
        mockMvc.perform(post("/pack")
                        .content("{\"uuid\":\"bd0e6ae8-afac-11ec-bd22-4b1a0b0df87b\", \"name\":\"Name 1\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
        mockMvc.perform(post("/pack")
                        .content("{\"uuid\":\"bd0e6ae8-afac-11ec-bd22-4b1a0b0df87b\", \"name\":\"Name 1\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void create_valid_pack_with_a_question() throws Exception {
        mockMvc.perform(post("/pack")
                        .content("{\"uuid\":\"b1c9dd8e-afb6-11ec-8d9f-0b56c5959e37\", \"name\":\"Name 1\"," +
                                "\"questions\": [\n" +
                                "        {\n" +
                                "            \"text\": \"Creamos pregunta 1 para pruebas\",\n" +
                                "            \"responses\": [\n" +
                                "                {\n" +
                                "                    \"text\": \"Texto 1\",\n" +
                                "                    \"order\": 1,\n" +
                                "                    \"correct\": true\n" +
                                "                }, \n" +
                                "                {\n" +
                                "                    \"text\": \"Texto 2\",\n" +
                                "                    \"order\": 2,\n" +
                                "                    \"correct\": false\n" +
                                "                },\n" +
                                "                {\n" +
                                "                    \"text\": \"Texto 3\",\n" +
                                "                    \"order\": 2,\n" +
                                "                    \"correct\": false\n" +
                                "                }\n" +
                                "            ]\n" +
                                "        }\n" +
                                "    ]" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

    }

}
