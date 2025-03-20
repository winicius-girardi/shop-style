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
    fun updateActiveById(id: Long, active: Boolean)

    @Transactional
    @Modifying
    @Query("UPDATE Category c SET  c.name=:name WHERE c.id = :id")
    fun updateFieldCategory(id:Long, name: String)

    @Query("""
    SELECT CASE 
               WHEN EXISTS (
                   SELECT 1
                   FROM ms_catalog_sch.category c
                   JOIN ms_catalog_sch.category d ON c.id = d.parent_id
                   WHERE c.id = :id and c.active = true
               ) THEN false
               ELSE true
           END as result
    """, nativeQuery = true)
    fun isValidCategoryById(id: Long): Boolean

}