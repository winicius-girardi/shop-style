package br.com.ms_catalog.controller.response

import br.com.ms_catalog.entity.Product

data class ProductResponse(  
    val id: Long,
    val name: String,
    val description: String,
    val brand: String,
    val material: String,
    val categoryId: Long
){

    companion object {
        fun toProductResponse(p: Product): ProductResponse {
            return ProductResponse(
                id = p.id,
                name = p.name,
                description = p.description,
                brand = p.brand,
                material = p.material,
                categoryId = p.categoryId
            )
        }
    }

}
