package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Sku
import org.springframework.data.jpa.repository.JpaRepository

interface SkuRepository :  JpaRepository<Sku, Long> {
}