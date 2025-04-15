package br.com.mscustomer.controller;

import br.com.mscustomer.entity.Address;
import br.com.mscustomer.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static br.com.mscustomer.factory.Factory.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class AddressControllerTest {

    @Container
    private final static PostgreSQLContainer<?> postgres= new PostgreSQLContainer<>("postgres:16")
            .withReuse(true);

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void shouldCreateAddressAndReturn200() throws Exception {
        mockMvc.perform(post("/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_ADDRESS))
            .andExpect(status().isCreated());
    }

    @Test
    void shouldGetErrorMessageWhileCreatingAddressAndReturn400() throws Exception {
        mockMvc.perform(post("/v1/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(INVALID_ADDRESS))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(INVALID_ADDRESS_RESPONSE));
    }

    @Test
    void shouldUpdateAddressAndReturn204() throws Exception {
        Optional<Address> address=addressRepository.findById(1L);

        mockMvc.perform(put("/v1/address/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_ADDRESS_CHANGE))
                .andExpect(status().isNoContent());

        Optional<Address> changedAddress=addressRepository.findById(1L);

        assertThat(address).isNotEqualTo(changedAddress);
    }

}
