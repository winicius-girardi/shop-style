package br.com.ms_catalog.controller.request

import br.com.ms_catalog.entity.Product

data class ProductRequest(
    val name: String,
    val description: String,
    val brand : String,
    val material : String,
    val active: Boolean,
    val categoryId: Long
){
    fun toProduct(): Product {
        return Product(
            id = 0,
            name = name,
            description = description,
            brand = brand,
            material = material,
            active = active,
            categoryId = categoryId
        )
    }
}
