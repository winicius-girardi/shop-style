package br.com.ms_catalog.controller

import br.com.ms_catalog.controller.request.SkuRequest
import br.com.ms_catalog.controller.request.SkuUpdateRequest
import br.com.ms_catalog.service.SkuService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class SkuController(val skuService: SkuService) {

    @PostMapping("/v1/skus")
    fun createSkus(@RequestBody request: SkuRequest): ResponseEntity<Void> {
        return skuService.createSkus(request)
    }

    @PutMapping("/v1/skus/{id}")
    fun changeSkus(@PathVariable id: Long,@RequestBody request: SkuUpdateRequest): ResponseEntity<Void> {
        return skuService.changeSkus(id, request)
    }

    @DeleteMapping("/v1/skus/{id}")
    fun deleteSkus(@PathVariable id: Long) : ResponseEntity<Void> {
        return skuService.deleteSkus(id)
    }

}