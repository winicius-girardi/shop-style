package br.com.ms_catalog.factory

import br.com.ms_catalog.entity.Category
import br.com.ms_catalog.entity.Product
import br.com.ms_catalog.entity.Sku



const val VALID_SKU =
    """{
        "price": 10.0,
        "quantity": 10,
        "color": "red",
        "size": "M",
        "height": 10.0,
        "width": 10.0,
        "images": ["image1","image2"],
        "productId": 1
    }"""

const val INVALID_SKU =
    """{
        "price":0 ,
        "quantity": 0,
        "color":"" ,
        "size":"" ,
        "height": 0,
        "width": 0,
        "images": [],
        "productId": 1
    }"""

const val VALID_SKU_WITH_PRODUCT_ID_INVALID =
    """{
        "price": 10.0,
        "quantity": 10,
        "color": "red",
        "size": "M",
        "height": 10.0,
        "width": 10.0,
        "images": ["image1","image2"],
        "productId": 9999
    }"""

const val VALID_SKU_CHANGE =
    """{
        "price": 10.1,
        "quantity": 10,
        "color": "blue",
        "size": "M",
        "height": 50.1,
        "width": 30.1
    }"""
const val ERROR_UPDATE_SKU=
    """[{
        "fieldName":"FIELDS",
        "message":"At least one field must be filled"
    }]"""

const val RESPONSE_INVALID_SKU_DATA=
    """[
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

const val RESPONSE_INVALID_PRODUCT_ID =
    """[{
        "fieldName":"productId",
        "message":"ProductId doesn't exist"
    }]"""

const val RESPONSE_ERROR_DATABASE=
    """[{
        "message": Error while trying to delete sku with id: 1
    }]"""

const val VALID_CATEGORY =
    """{
        "name":"cavalo",
        "active":true,
        "parentId":1
    }"""

const val INVALID_CATEGORY=
    """{
        "name":"",
        "active":"",
        "parentId":999
    }"""

const val RESPONSE_MEDIA_INVALID=
    """[{
        "fieldName":"name",
        "message":"Name is required"},
        {
        "fieldName":"parentId",
        "message":"ParentId doesn't exist"
    }]"""

const val VALID_UPDATE_CATEGORY=
    """{
       "name": "teste_teste" 
    }"""

const val INVALID_UPDATE_CATEGORY=
    """{
        "name": ""
    }"""

const val RESPONSE_INVALID_MEDIA_UPDATE=
    """[{
        "fieldName":"name",
        "message":"name cannot be blank"
    }]"""

const val RESPONSE_ALL_CATEGORY=
    """[{
        "name":"Roupas",
        "active":true,
        "id":1,
        "children":[{
            "name":"Calças",
            "active":true,
            "id":2
            },
            {
            "name":"Camisas",
            "active":true,
            "id":3
            }
        ]
        }]"""


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

