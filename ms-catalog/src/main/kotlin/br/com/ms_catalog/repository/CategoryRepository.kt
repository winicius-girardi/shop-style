package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface CategoryRepository :JpaRepository<Category, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Category c SET c.active = :active WHERE c.id = :id")
    fun updateActiveById(id: Long, active: Boolean){

    }

    @Query(value = """
    WITH RECURSIVE category_tree AS (
        SELECT id, name, active, parent_id
        FROM MS_CATALOG_SCH.CATEGORY
        WHERE id = :parentId
        UNION ALL
        SELECT c.id, c.name, c.active, c.parent_id
        FROM MS_CATALOG_SCH.CATEGORY c
        INNER JOIN category_tree ct ON c.parent_id = ct.id
    )
    SELECT * FROM category_tree;
    """, nativeQuery = true)
    fun findCategoryTree(@Param("parentId") parentId: Long): List<Category>


}