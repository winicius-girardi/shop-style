package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository  : JpaRepository<Product, Long> {
}