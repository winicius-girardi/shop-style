package br.com.ms_catalog.utils

import br.com.ms_catalog.controller.request.CategoryRequest
import br.com.ms_catalog.controller.request.ProductRequest
import br.com.ms_catalog.controller.response.ErrorField
import br.com.ms_catalog.repository.CategoryRepository


fun validateCategory(request: CategoryRequest,categoryRepository: CategoryRepository): List<ErrorField> {
    val errors = mutableListOf<ErrorField>()

    if (request.name.isEmpty())
         errors.add(ErrorField("name", "Name is required"))

    if(request.parentId != null || request.parentId==0L) {
        if (request.parentId < 0)
            errors.add(ErrorField("parentId", "ParentId is required"))
        categoryRepository.findById(request.parentId).ifPresentOrElse(
            { },
            { errors.add(ErrorField("parentId", "ParentId doesn't exist")) }
        )
    }
    return errors
}

fun validateProduct(request: ProductRequest, categoryRepository: CategoryRepository): List<ErrorField> {
    val errors = mutableListOf<ErrorField>()

    if (request.name.isEmpty())
        errors.add(ErrorField("name", "Name is required"))

    if (request.description.isEmpty())
        errors.add(ErrorField("description", "Description is required"))

    if (request.brand.isEmpty())
        errors.add(ErrorField("brand", "Brand is required"))

    if (request.material.isEmpty())
        errors.add(ErrorField("material", "Material is required"))

    if (request.categoryId < 0)
        errors.add(ErrorField("categoryId", "CategoryId is required"))

    if(!categoryRepository.isValidCategoryById(request.categoryId))
        errors.add(ErrorField("categoryId", "CategoryId has children or is inactive"))

    return errors
}