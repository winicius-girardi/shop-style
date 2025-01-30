package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository :JpaRepository<Category, Long> {

}