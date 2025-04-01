package br.com.ms_catalog.service

import br.com.ms_catalog.controller.request.SkuRequest
import br.com.ms_catalog.controller.request.SkuUpdateRequest
import br.com.ms_catalog.controller.response.ErrorField
import br.com.ms_catalog.entity.Media
import br.com.ms_catalog.exception.DatabaseException
import br.com.ms_catalog.exception.ValidationException
import br.com.ms_catalog.repository.MediaRepository
import br.com.ms_catalog.repository.ProductRepository
import br.com.ms_catalog.repository.SkuRepository
import br.com.ms_catalog.utils.validateSku
import br.com.ms_catalog.utils.validateSkuUpdate
import jakarta.persistence.EntityManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SkuService(val skuRepository: SkuRepository,
                 val productRepository: ProductRepository,
                 val mediaRepository: MediaRepository,
                 val entityManager: EntityManager) {

    @Transactional
    fun createSkus(sku: SkuRequest) :ResponseEntity<Void> {
        val errors= validateSku(sku, productRepository)

        if(errors.isNotEmpty())
            throw ValidationException(errors)

        val skuSave =skuRepository.save(sku.toSkuEntity())
        val mediaList=sku.images.map { img -> Media(0,img,skuSave.id)}
        mediaRepository.saveAll(mediaList)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Transactional
    fun changeSkus(id: Long,request: SkuUpdateRequest):ResponseEntity<Void> {
        //TODO -> VALIDAR SE O ID PASSADO EXISTS AKA O SKU QUE SERA ALTERADO EXISTE NO BANCO

        val errors=validateSkuUpdate(request)

        if(errors.isNotEmpty())
            throw ValidationException(errors)
        val queryParts = mutableListOf<String>()
        val params = mutableMapOf<String, Any>()

        if (request.price != null) {
            queryParts.add("s.price = :price")
            params["price"] = request.price
        }

        if (request.quantity != null) {
            queryParts.add("s.quantity = :quantity")
            params["quantity"] = request.quantity
        }

        if (!request.color.isNullOrBlank()) {
            queryParts.add("s.color = :color")
            params["color"] = request.color
        }

        if (!request.size.isNullOrBlank()) {
            queryParts.add("s.size = :size")
            params["size"] = request.size
        }

        if (request.height != null) {
            queryParts.add("s.height = :height")
            params["height"] = request.height
        }

        if (request.width != null) {
            queryParts.add("s.width = :width")
            params["width"] = request.width
        }

        if (queryParts.isEmpty()) {
            throw ValidationException(listOf(ErrorField("FIELDS","At least one field must be filled")))        }

        val queryString = "UPDATE Sku s SET ${queryParts.joinToString(", ")} WHERE s.id = :id"
        val query = entityManager.createQuery(queryString)

        params.forEach { (key, value) -> query.setParameter(key, value) }
        query.setParameter("id", id)

        query.executeUpdate()

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @Transactional
    fun deleteSkus(id: Long) :ResponseEntity<Void> {
        try {
            mediaRepository.deleteBySkuId(id)
            skuRepository.deleteBySkuId(id)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }
        catch (ex: Exception){
            throw DatabaseException("Error while trying to delete sku with id: $id")
        }
    }
}