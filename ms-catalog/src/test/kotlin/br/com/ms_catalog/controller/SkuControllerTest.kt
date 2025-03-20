package br.com.ms_catalog.controller

import br.com.ms_catalog.factory.INVALID_SKU
import br.com.ms_catalog.factory.RESPONSE_INVALID_SKU
import br.com.ms_catalog.factory.VALID_SKU
import br.com.ms_catalog.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class SkuControllerTest {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    companion object {
        @Container
        @JvmStatic
        @ServiceConnection
        val postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:16")
            .withReuse(true)
//            .withCopyFileToContainer(
//                MountableFile.forClasspathResource("schema.sql"), "/docker-entrypoint-initdb.d/schema.sql"
//            )
    }


    @Test
    @Order(1)
    fun `should create Sku with success and return 201`() {

        mockMvc.perform(
            post("/v1/skus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_SKU))
            .andExpect(status().isCreated)

        assertThat(productRepository.findAll().size).isEqualTo(1)

    }
    @Test
    @Order(2)
    fun `should get error message creating Sku with invalid data and return 400`() {

        mockMvc.perform(
            post("/v1/skus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INVALID_SKU))
            .andExpect(status().isBadRequest)
            .andExpect(content().json(RESPONSE_INVALID_SKU))
//
//            .andExpect(jsonPath("$.[0].field").value("price"))
//            .andExpect(jsonPath("$.[0].message").value("Price must be greater than 0"))
//            .andExpect(jsonPath("$.[1].field").value("quantity"))
//            .andExpect(jsonPath("$.[1].message").value("quantity cannot be null"))
//            .andExpect(jsonPath("$.[2].field").value("color"))
//            .andExpect(jsonPath("$.[2].message").value("color cannot be null"))
//            .andExpect(jsonPath("$.[3].field").value("size"))
//            .andExpect(jsonPath("$.[3].message").value("size cannot be null"))
//            .andExpect(jsonPath("$.[4].field").value("height"))
//            .andExpect(jsonPath("$.[4].message").value("height cannot be null"))
//            .andExpect(jsonPath("$.[5].field").value("width"))
//            .andExpect(jsonPath("$.[5].message").value("width cannot be null"))
//            .andExpect(jsonPath("$.[6].field").value("images"))
//            .andExpect(jsonPath("$.[6].message").value("images cannot be null"))

    }















    @Test
    @Order(2)
    fun `should create category father with success and return 201`() {

        mockMvc.perform(
            post("/v1/skus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_SKU))
            .andExpect(status().isCreated)


    }
}