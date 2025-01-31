package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface CategoryRepository :JpaRepository<Category, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Category c SET c.active = :active WHERE c.id = :id")
    fun updateActiveById(id: Long, active: Boolean){

    }
}