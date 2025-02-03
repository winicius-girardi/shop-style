package br.com.ms_catalog.service

import br.com.ms_catalog.controller.request.CategoryRequest
import br.com.ms_catalog.controller.response.CategoryTreeResponse
import br.com.ms_catalog.entity.Category
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

        if(validation.isNotEmpty())
            throw ValidationException(validation)

        categoryRepository.save(request.toCategoryEntity())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    fun deleteCategory(id: Long) : ResponseEntity<Void> {

        categoryRepository.updateActiveById(id,false)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    fun getAllCategories() :ResponseEntity<List<CategoryTreeResponse>> {
        val categoryList = categoryRepository.findAll()
        val categoryTree = buildHierarchy(categoryList)
        return ResponseEntity.status(HttpStatus.OK).body(categoryTree)
    }

    fun buildHierarchy(categoryList: List<Category>): List<CategoryTreeResponse> {

        val rootCategories =  categoryList.filter { it.parentId == null }

        return rootCategories
            .map { category ->
                CategoryTreeResponse(
                    category.name,
                    category.active,
                    category.id,
                    buildChildren(categoryList, category.id)
                )
            }
    }

    fun buildChildren(categoryList: List<Category>, parentId: Long): List<CategoryTreeResponse>? {
        val children =categoryList
            .filter { it.parentId == parentId }
            .map { category ->
                CategoryTreeResponse(
                    category.name,
                    category.active,
                    category.id,
                    buildChildren(categoryList, category.id)
                )
            }
        return children.ifEmpty { null }
    }
}
