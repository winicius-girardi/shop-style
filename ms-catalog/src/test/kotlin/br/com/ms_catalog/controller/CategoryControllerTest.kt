package br.com.ms_catalog.controller

import br.com.ms_catalog.factory.*
import br.com.ms_catalog.repository.CategoryRepository
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
class CategoryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

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
    fun `should get all categories and return 200`(){

        mockMvc.perform(
            get("/v1/categories"))
            .andExpect(status().isOk)
            .andExpect(content().json(RESPONSE_ALL_CATEGORY))

    }


    @Test
    @Order(3)
    fun `should create category with success and return 201`() {

        val sizeCategory= categoryRepository.findAll().size

        mockMvc.perform(
            post("/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_CATEGORY))
            .andExpect(status().isCreated)

        assertThat(categoryRepository.findAll().size).isGreaterThan(sizeCategory)
    }

    @Test
    @Order(2)
    fun `should get error while creating category with invalid data and return 400`(){

        mockMvc.perform(
            post("/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INVALID_CATEGORY))
            .andExpect(status().isBadRequest)
            .andExpect(content().json(RESPONSE_MEDIA_INVALID))

    }

    @Test
    @Order(6)
    fun `should delete category by id`(){

        assertThat(categoryRepository.findById(1).isPresent)

        mockMvc.perform(
            delete("/v1/categories/1"))
            .andExpect(status().isNoContent)

        assertThat(categoryRepository.findById(1).isEmpty)
    }

    @Test
    @Order(4)
    fun `should  update category with sucess and return 200`(){

        mockMvc.perform(
            put("/v1/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_UPDATE_CATEGORY))
            .andExpect(status().isOk)
    }
    @Test
    @Order(5)
    fun `should  get error while updating category with invalid data and return 400`(){

        mockMvc.perform(
            put("/v1/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INVALID_UPDATE_CATEGORY))
            .andExpect(status().isBadRequest)
            .andExpect(content().json(RESPONSE_INVALID_MEDIA_UPDATE))
    }



}