package br.com.ms_catalog.service

import br.com.ms_catalog.controller.request.ProductRequest
import br.com.ms_catalog.controller.response.ErrorField
import br.com.ms_catalog.controller.response.ProductResponse
import br.com.ms_catalog.exception.DatabaseException
import br.com.ms_catalog.exception.ValidationException
import br.com.ms_catalog.repository.CategoryRepository
import br.com.ms_catalog.repository.ProductRepository
import br.com.ms_catalog.utils.validateProduct
import jakarta.persistence.EntityManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ProductService(val  productRepository: ProductRepository,val categoryRepository: CategoryRepository,
                     val entityManager: EntityManager) {

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
        return ResponseEntity.status(HttpStatus.OK).body(ProductResponse.toProductResponse(
            productRepository.findById(id)
                .orElseThrow{DatabaseException("Error while trying to find product by id: $id")})
        )
    }

    fun getAllProducts():ResponseEntity<List<ProductResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll().map {
            ProductResponse.toProductResponse(it)})
    }

    @Transactional
    fun updateProduct(id: Long, name: String?, description: String?, brand: String?, material: String?):ResponseEntity<Void>{
        val queryParts = mutableListOf<String>()
        val params = mutableMapOf<String, Any>()

        if (!name.isNullOrBlank()) {
            queryParts.add("p.name = :name")
            params["name"] = name
        }
        if (!description.isNullOrBlank()) {
            queryParts.add("p.description = :description")
            params["description"] = description
        }
        if (!brand.isNullOrBlank()) {
            queryParts.add("p.brand = :brand")
            params["brand"] = brand
        }
        if (!material.isNullOrBlank()) {
            queryParts.add("p.material = :material")
            params["material"] = material
        }

        if (queryParts.isEmpty())
            throw ValidationException(listOf(ErrorField("FIELDS","At least one field must be filled")))


        val queryString = "UPDATE Product p SET ${queryParts.joinToString(", ")} WHERE p.id = :id"
        val query = entityManager.createQuery(queryString)

        params.forEach { (key, value) -> query.setParameter(key, value) }
        query.setParameter("id", id)

        query.executeUpdate()
        return ResponseEntity(HttpStatus.NO_CONTENT)

    }


}