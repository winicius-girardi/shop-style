package br.com.ms_catalog.controller

import br.com.ms_catalog.factory.INVALID_PRODUCT
import br.com.ms_catalog.factory.RESPONSE_ALL_PRODUCTS
import br.com.ms_catalog.factory.RESPONSE_INVALID_PRODUCT
import br.com.ms_catalog.factory.VALID_PRODUCT
import br.com.ms_catalog.repository.CategoryRepository
import br.com.ms_catalog.repository.ProductRepository
import br.com.ms_catalog.service.ProductService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProductControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    companion object {
        @Container
        @JvmStatic
        @ServiceConnection
        val postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:16")
            .withReuse(true)
    }


    @Test
    @Order(1)
    fun `should get all products and return 200`(){

        mockMvc.perform(
            get("/v1/products"))
            .andExpect(status().isOk)
            .andExpect(content().json(RESPONSE_ALL_PRODUCTS))

    }


    @Test
    @Order(3)
    fun `should create product with sucess and return 201`(){

        val sizeProduct = productRepository.findAll().size

        mockMvc.perform(
            post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_PRODUCT))
            .andExpect(status().isCreated)

        assertThat(productRepository.findAll().size).isGreaterThan(sizeProduct)
    }

    @Test
    @Order(2)
    fun `should get error attempting to create a product with invalid data and return 400`(){

        mockMvc.perform(
            post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INVALID_PRODUCT))
            .andExpect(status().isBadRequest)
            .andExpect(content().json(RESPONSE_INVALID_PRODUCT))

    }

    @Test
    @Order(4)
    fun `should delete a product and return 204`(){


        assertThat(productRepository.findById(1).get().active).isTrue

        mockMvc.perform(
            delete("/v1/products/1"))
            .andExpect(status().isNoContent)

        assertThat(productRepository.findById(1).get().active).isFalse
    }
}