package br.com.ms_catalog.repository

import br.com.ms_catalog.entity.Media
import org.springframework.data.jpa.repository.JpaRepository

interface MediaRepository : JpaRepository<Media, Long> {
}