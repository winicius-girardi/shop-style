package br.com.ms_catalog.controller.request

import br.com.ms_catalog.entity.Media
import br.com.ms_catalog.entity.Sku

data class SkuRequest(
    val price: Double,
    val quantity: Int,
    val color: String,
    val size: String,
    val height: Double,
    val width: Double,
    val images: MutableList<String>,
    val productId: Long
){
    fun toSkuEntity(): Sku {
        return Sku(
            id=0L,
            price = this.price,
            quantity = this.quantity,
            color = this.color,
            size = this.size,
            height = this.height,
            width = this.width,
            productId = this.productId
        )
    }
    fun toMediaEntity(skuId: Long,url:String): Media {
        return Media(
            id=0L,
            imageUrl = url,
            skuId = skuId
        )
    }
}
