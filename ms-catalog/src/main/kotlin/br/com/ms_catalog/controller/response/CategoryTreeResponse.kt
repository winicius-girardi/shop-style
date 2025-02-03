package br.com.ms_catalog.controller.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CategoryTreeResponse(val name:String,
                                val active: Boolean,
                                val id: Long,
                                val children: List<CategoryTreeResponse>? = null)

