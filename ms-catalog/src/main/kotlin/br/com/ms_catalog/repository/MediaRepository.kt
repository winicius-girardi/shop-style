package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Media
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.http.ResponseEntity

interface MediaRepository : JpaRepository<Media, Long> {

    @Modifying
    @Query("DELETE FROM Media WHERE skuId = :id")
    fun deleteBySkuId(id: Long)
}