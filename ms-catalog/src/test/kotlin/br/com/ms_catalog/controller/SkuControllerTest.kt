package br.com.ms_catalog.controller

import br.com.ms_catalog.factory.*
import br.com.ms_catalog.repository.MediaRepository
import br.com.ms_catalog.repository.SkuRepository
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
class SkuControllerTest {

    @Autowired
    private lateinit var skuRepository: SkuRepository

    @Autowired
    private lateinit var mediaRepository: MediaRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    companion object {
        @Container
        @JvmStatic
        @ServiceConnection
        val postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:16")
            .withReuse(true)
    }

     //TODO -> TEST WHERE THE DB IS DOWN
    @Test
    @Order(1)
    fun `should create Sku with success and return 201`() {

        assertThat(skuRepository.findAll().size).isEqualTo(1)

        mockMvc.perform(
            post("/v1/skus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_SKU))
            .andExpect(status().isCreated)

        assertThat(skuRepository.findAll().size).isEqualTo(2)
    }

    @Test
    @Order(2)
    fun `should get error message creating Sku with invalid data and return 400`() {

        mockMvc.perform(
            post("/v1/skus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INVALID_SKU))
            .andExpect(status().isBadRequest)
            .andExpect(content().json(RESPONSE_INVALID_SKU_DATA))
    }

    @Test
    @Order(3)
    fun `should get error message creating Sku with duplicate id and return 400`() {

        mockMvc.perform(
            post("/v1/skus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_SKU_WITH_PRODUCT_ID_INVALID))
            .andExpect(status().isBadRequest)
            .andExpect(content().json(RESPONSE_INVALID_PRODUCT_ID))
    }

    @Test
    @Order(4)
    fun `should alter a existing sku with sucess and return 204`(){

        mockMvc.perform(
            put("/v1/skus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_SKU_CHANGE))
        .andExpect(status().isNoContent)
    }

    //TODO -> TEST WHERE THE ID OF PATH PARAM DONT EXIST IN DB
    @Test
    @Order(5)
    fun `should get error while alter a existing sku and return 400`(){

        mockMvc.perform(
            put("/v1/skus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest)
            .andExpect(content().json(ERROR_UPDATE_SKU))
    }

    @Test
    @Order(6)
    fun `should delete sku and return 204`(){

        val sizeSku=skuRepository.findAll().size
        val sizeMedia=mediaRepository.findAll().size

        mockMvc.perform(
            delete("/v1/skus/1"))
                .andExpect(status().isNoContent)

        assertThat(skuRepository.findAll().size).isLessThan(sizeSku)
        assertThat(mediaRepository.findAll().size).isLessThan(sizeMedia)
    }
// VER COMO TESTAR DELETE ENDPOINT DANDO EXCECAO
//    @Test
//    @Order(7)
//    fun `should get error while deleting and return 400`(){
//
//        mockMvc.perform(
//            delete("/v1/skus/1"))
//                .andExpect(status().isBadRequest)
//                .andExpect(content().json(RESPONSE_ERROR_DATABASE))
//
//
//    }


















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