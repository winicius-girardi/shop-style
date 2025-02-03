package br.com.ms_catalog.service

import br.com.ms_catalog.controller.request.ProductRequest
import br.com.ms_catalog.controller.response.ProductResponse
import br.com.ms_catalog.exception.DatabaseException
import br.com.ms_catalog.exception.ValidationException
import br.com.ms_catalog.repository.CategoryRepository
import br.com.ms_catalog.repository.ProductRepository
import br.com.ms_catalog.utils.validateProduct
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class ProductService(val  productRepository: ProductRepository,val categoryRepository: CategoryRepository) {

    fun createProduct(request: ProductRequest): ResponseEntity<Void> {

        val errors=validateProduct(request,categoryRepository)

        if(errors.isNotEmpty())
            throw ValidationException(errors)

        productRepository.save(request.toProduct())

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    fun deleteProduct(id: Long) : ResponseEntity<Void> {
        productRepository.updateActiveById(id,false)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    fun getProduct(id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(ProductResponse.toProductResponse(productRepository.findById(id)
            .orElseThrow{DatabaseException("Error while trying to find product by id: $id")})
        )
    }

    fun getAllProducts():ResponseEntity<List<ProductResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll().map { ProductResponse.toProductResponse(it)})
    }

}