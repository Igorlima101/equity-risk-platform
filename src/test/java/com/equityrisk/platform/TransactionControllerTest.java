
package com.equityrisk.platform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.JsonNode;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private tools.jackson.databind.ObjectMapper objectMapper;

    private String requestBody = """
        {
            "customerId": 15,
            "amount": 5000.00,
            "currency": "BRL"
        }
        """;

    private Long createTransactionAndGetId() throws Exception {

        MvcResult result = mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode response = objectMapper.readTree(
                result.getResponse().getContentAsString()
        );

        return response.get("id").asLong();
    }

    @Test
    void shouldCreateTransactionAndReturnResponse() throws Exception {

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.customerId").value(15))
                .andExpect(jsonPath("$.amount").value(5000.00))
                .andExpect(jsonPath("$.currency").value("BRL"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void shouldGetTransactionById() throws Exception {

        Long id = createTransactionAndGetId();

        mockMvc.perform(get("/api/transactions/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.customerId").value(15))
                .andExpect(jsonPath("$.amount").value(5000.00))
                .andExpect(jsonPath("$.currency").value("BRL"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void shouldReturn404WhenTransactionDoesNotExist() throws Exception {

        mockMvc.perform(get("/api/transactions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message")
                        .value("Transaction not found with ID: " + Long.MAX_VALUE));
    }

    @Test
    void shouldReturnTransactionsWithPagination() throws Exception {
        mockMvc.perform(get("/api/transactions")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.page.size").value(2))
                .andExpect(jsonPath("$.page.number").value(0));
    }

    @Test
    void shouldReturn400WhenPageIsNegative() throws Exception {
        mockMvc.perform(get("/api/transactions")
                        .param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    void shouldReturn400WhenSizeIsZero() throws Exception {
        mockMvc.perform(get("/api/transactions")
                        .param("size", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }
}
