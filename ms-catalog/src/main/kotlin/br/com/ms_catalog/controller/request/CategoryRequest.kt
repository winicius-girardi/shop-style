package br.com.ms_catalog.controller.request

import br.com.ms_catalog.entity.Category

data class CategoryRequest(
    val name: String,
    val active: Boolean,
    val parentId: Long
){
    fun toCategoryEntity(): Category {
        return Category(
            id = 0,
            name = this.name,
            active = this.active,
            parentId = this.parentId
        )
    }
}
