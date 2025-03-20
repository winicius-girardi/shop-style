package br.com.ms_catalog.factory

import br.com.ms_catalog.entity.Category
import br.com.ms_catalog.entity.Product
import br.com.ms_catalog.entity.Sku



const val VALID_SKU = """{
                        "price": 10.0,
                        "quantity": 10,
                        "color": "red",
                        "size": "M",
                        "height": 10.0,
                        "width": 10.0,
                        "images": ["image1","image2"],
                        "productId": 1
                        }"""

const val INVALID_SKU = """{
                            "price":0 ,
                            "quantity": 0,
                            "color":"" ,
                            "size":"" ,
                            "height": 0,
                            "width": 0,
                            "images": [],
                            "productId": 1
                            }"""

const val RESPONSE_INVALID_SKU="""[
                        {
                            "fieldName": "price",
                            "message": "Price must be greater than 0"
                        },
                        {
                            "fieldName": "quantity",
                            "message": "Quantity must be greater than 0"
                        },
                        {
                            "fieldName": "color",
                            "message": "Color is required"
                        },
                        {
                            "fieldName": "size",
                            "message": "Size is required"
                        },
                        {
                            "fieldName": "height",
                            "message": "Height must be greater than 0"
                        },
                        {
                            "fieldName": "width",
                            "message": "Width must be greater than 0"
                        },
                        {
                            "fieldName": "images",
                            "message": "Images is required"
                        }
                        ]"""


fun getProduct(): Product {
    return Product(
        id = 0L,
        name = "Product Test",
        description = "Product Test Description",
        brand = "Brand Test",
        material = "Material Test",
        active = true,
        categoryId = 1L
    )
}
fun getCategogyFather():Category{
    return Category(
        id = 0L,
        name = "Category Test",
        active = true,
        parentId = null
    )
}
fun getCategoryChildren():Category{
    return Category(
        id = 0L,
        name = "Category Children Test",
        active = true,
        parentId = 1L
    )
}
fun getSku(): Sku {
    return Sku(
        id = 0L,
        price = 10.0,
        quantity = 10,
        color = "red",
        size = "M",
        height = 10.0,
        width = 10.0,
        productId = 1L
    )
}

