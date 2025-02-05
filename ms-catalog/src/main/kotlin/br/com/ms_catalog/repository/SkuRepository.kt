package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Sku
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface SkuRepository :  JpaRepository<Sku, Long> {

    @Modifying
    @Query("DELETE FROM Sku s WHERE s.productId = :id")
    fun deleteBySkuId(id: Long)
}