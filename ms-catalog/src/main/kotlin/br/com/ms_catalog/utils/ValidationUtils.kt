package br.com.ms_catalog.utils

import br.com.ms_catalog.controller.request.CategoryRequest
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