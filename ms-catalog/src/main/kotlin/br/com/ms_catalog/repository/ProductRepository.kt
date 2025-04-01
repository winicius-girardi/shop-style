package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface ProductRepository  : JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.active = :active WHERE p.id = :id")
    fun updateActiveById(id: Long, active: Boolean)
}