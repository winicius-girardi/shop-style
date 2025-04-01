package br.com.ms_catalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MsCatalogApplication

fun main(args: Array<String>) {
	runApplication<MsCatalogApplication>(*args)
}
