package br.com.ms_catalog.service

import br.com.ms_catalog.controller.request.SkuRequest
import br.com.ms_catalog.entity.Media
import br.com.ms_catalog.exception.ValidationException
import br.com.ms_catalog.repository.MediaRepository
import br.com.ms_catalog.repository.ProductRepository
import br.com.ms_catalog.repository.SkuRepository
import br.com.ms_catalog.utils.validateSku
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SkuService(val skuRepository: SkuRepository,val productRepository: ProductRepository,val mediaRepository: MediaRepository) {

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

    fun changeSkus(id: String) {
    }

    fun deleteSkus(id: String) {
    }
}