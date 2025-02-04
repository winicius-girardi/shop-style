package br.com.ms_catalog.controller

import br.com.ms_catalog.controller.request.SkuRequest
import br.com.ms_catalog.service.SkuService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class SkuController(val skuService: SkuService) {

    @PostMapping("/v1/skus")
    fun createSkus(@RequestBody request: SkuRequest): ResponseEntity<Void> {
        return skuService.createSkus(request)
    }

    @PutMapping("/v1/skus/{id}")
    fun changeSkus(@PathVariable id: String) {
    }

    @DeleteMapping("/v1/skus/{id}")
    fun deleteSkus(@PathVariable id: String) {
    }

}