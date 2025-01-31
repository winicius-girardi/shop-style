package br.com.ms_catalog.service

import br.com.ms_catalog.controller.request.CategoryRequest
import br.com.ms_catalog.exception.ValidationException
import br.com.ms_catalog.repository.CategoryRepository
import br.com.ms_catalog.utils.validateCategory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository){

    fun createCategory(request: CategoryRequest): ResponseEntity<Void> {

        val validation= validateCategory(request,categoryRepository)

        if(validation.isEmpty())
            throw ValidationException(validation)

        categoryRepository.save(request.toCategoryEntity())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}
