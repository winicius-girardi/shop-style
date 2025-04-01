package br.com.ms_catalog.controller.request

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProductUpdateRequest(
    val name: String?,
    val description: String?,
    val brand: String?,
    val material: String?
)
