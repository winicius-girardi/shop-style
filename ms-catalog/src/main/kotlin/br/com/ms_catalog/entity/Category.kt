package br.com.ms_catalog.entity

import jakarta.persistence.*

@Entity
@Table(name = "CATEGORY", schema = "MS_CATALOG_SCH")
class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "NAME", nullable = false)
    var name: String,

    @Column(name = "active", nullable = false)
    var active: Boolean,

    @Column(name = "PARENT_ID", nullable = false)
    var parentId: Long?
)