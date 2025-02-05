package br.com.ms_catalog.utils

import br.com.ms_catalog.controller.request.CategoryRequest
import br.com.ms_catalog.controller.request.ProductRequest
import br.com.ms_catalog.controller.request.SkuRequest
import br.com.ms_catalog.controller.request.SkuUpdateRequest
import br.com.ms_catalog.controller.response.ErrorField
import br.com.ms_catalog.repository.CategoryRepository
import br.com.ms_catalog.repository.ProductRepository


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

fun validateSku(sku:SkuRequest,productRepository: ProductRepository): List<ErrorField> {
    val errors = mutableListOf<ErrorField>()

    if(sku.price < 0)
        errors.add(ErrorField("price","Price must be greater than 0"))

    if(sku.quantity < 0)
        errors.add(ErrorField("quantity","Quantity must be greater than 0"))

    if(sku.color.isEmpty())
        errors.add(ErrorField("color","Color is required"))

    if(sku.size.isEmpty())
        errors.add(ErrorField("size","Size is required"))

    if(sku.height < 0)
        errors.add(ErrorField("height","Height must be greater than 0"))

    if(sku.width < 0)
        errors.add(ErrorField("width","Width must be greater than 0"))

    if(sku.productId < 0)
        errors.add(ErrorField("productId","ProductId is required"))

    if(!productRepository.existsById(sku.productId))
        errors.add(ErrorField("productId","ProductId doesn't exist"))

    return errors

}
fun validateSkuUpdate(sku:SkuUpdateRequest): List<ErrorField> {
    val errors = mutableListOf<ErrorField>()

    if(sku.price!=null&& sku.price < 0)
        errors.add(ErrorField("price","Price must be greater than 0"))

    if(sku.quantity!=null&&sku.quantity < 0)
        errors.add(ErrorField("quantity","Quantity must be greater than 0"))

    if(sku.height!=null&&sku.height < 0)
        errors.add(ErrorField("height","Height must be greater than 0"))

    if(sku.width!=null&&sku.width < 0)
        errors.add(ErrorField("width","Width must be greater than 0"))

    return errors

}
