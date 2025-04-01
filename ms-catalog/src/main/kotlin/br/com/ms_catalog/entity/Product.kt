package br.com.ms_catalog.entity

import jakarta.persistence.*

@Entity
@Table(name = "PRODUCT", schema = "MS_CATALOG_SCH")
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long,

    @Column(name = "NAME", nullable = false)
    var name: String,

    @Column(name = "DESCRIPTION", nullable = false)
    var description: String,

    @Column(name = "BRAND", nullable = false)
    var brand: String,

    @Column(name = "MATERIAL", nullable = false)
    var material: String,

    @Column(name = "ACTIVE", nullable = false)
    var active: Boolean,

    @Column(name = "CATEGORY_ID", nullable = false)
    var categoryId: Long
)