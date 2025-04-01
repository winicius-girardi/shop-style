package br.com.ms_catalog.controller.request

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
data class SkuUpdateRequest(
    val price: Double?,
    val quantity: Int?,
    val color: String?,
    val size: String?,
    val height: Double?,
    val width: Double?
)

