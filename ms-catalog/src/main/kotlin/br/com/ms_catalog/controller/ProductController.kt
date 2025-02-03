package br.com.ms_catalog.controller

import br.com.ms_catalog.controller.request.ProductRequest
import br.com.ms_catalog.controller.response.ProductResponse
import br.com.ms_catalog.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class ProductController(val  productService: ProductService) {

    @DeleteMapping("/v1/products/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return productService.deleteProduct(id)
    }

    @GetMapping("/v1/products/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return productService.getProduct(id)
    }

    @GetMapping("/v1/products")
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        return productService.getAllProducts()
    }

    @PostMapping("/v1/products")
    fun createProduct(@RequestBody request: ProductRequest)  : ResponseEntity<Void> {
        return productService.createProduct(request)
    }

    @PutMapping("/v1/products/{id}")
    fun changeProduct(@PathVariable id: String){
    }
}