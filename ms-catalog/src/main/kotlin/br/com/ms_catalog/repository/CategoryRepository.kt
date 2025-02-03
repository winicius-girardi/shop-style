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

    // select  c.id from ms_catalog_sch.category c join ms_catalog_sch.category d on c.id=d.parent_Id where c.active=true and c.id=1 ; --funfa
    //@Query("SELECT C.id,C.active FROM Category C INNER JOIN Category D WHERE C.active = true AND C.id = :id")
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



//    @Query(value = """
//    WITH RECURSIVE category_tree AS (
//        SELECT id, name, active, parent_id
//        FROM MS_CATALOG_SCH.CATEGORY
//        WHERE id = :parentId
//        UNION ALL
//        SELECT c.id, c.name, c.active, c.parent_id
//        FROM MS_CATALOG_SCH.CATEGORY c
//        INNER JOIN category_tree ct ON c.parent_id = ct.id
//    )
//    SELECT * FROM category_tree;
//    """, nativeQuery = true)
//    fun findCategoryTree(@Param("parentId") parentId: Long): List<Category>


}