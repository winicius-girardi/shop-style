package br.com.ms_catalog.controller

import br.com.ms_catalog.controller.request.CategoryRequest
import br.com.ms_catalog.controller.response.CategoryTreeResponse
import br.com.ms_catalog.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class CategoryController (private val categoryService: CategoryService) {

    @PostMapping("/v1/categories")
    fun createCategories(@RequestBody request: CategoryRequest): ResponseEntity<Void>{
        return categoryService.createCategory(request)
    }

    @DeleteMapping("/v1/categories/{id}")
    fun deleteCategories(@PathVariable id: Long): ResponseEntity<Void>{
        return categoryService.deleteCategory(id)
    }

    @GetMapping("/v1/categories/{id}/products")
    fun getCategoriesWithChildren(@PathVariable id: Long): ResponseEntity<List<CategoryTreeResponse>>{
        return categoryService.getCategoryWithChidren(id)
    }


    @PutMapping("/v1/categories/{id}")
    fun changeCategories(@PathVariable id: Long) {
    }

    @GetMapping("v1/categories")
    fun getCategories() {
    }
}