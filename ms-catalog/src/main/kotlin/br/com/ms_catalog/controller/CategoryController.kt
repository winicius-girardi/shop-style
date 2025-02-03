package br.com.ms_catalog.controller

import br.com.ms_catalog.controller.request.CategoryRequest
import br.com.ms_catalog.controller.request.CategoryUpdateRequest
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

    //TODO -> is return categories of a product by id(product) or return products of a category by id(category)?
    @GetMapping("/v1/categories/{id}/products")
    fun getProductsByCategoryId(@PathVariable id: Long){
        return
    }


    @PutMapping("/v1/categories/{id}")
    fun changeCategoryName(@PathVariable id: Long,@RequestBody request: CategoryUpdateRequest): ResponseEntity<Void>{
        return categoryService.updateCategory(id,request)
    }

    @GetMapping("v1/categories")
    fun getAllCategories():ResponseEntity<List<CategoryTreeResponse>>{
        return categoryService.getAllCategories()
    }
}