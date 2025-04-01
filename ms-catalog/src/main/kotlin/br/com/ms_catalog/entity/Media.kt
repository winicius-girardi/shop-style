package br.com.ms_catalog.entity

import jakarta.persistence.*

@Entity
@Table(name = "MEDIA", schema = "MS_CATALOG_SCH")
class Media(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "IMAGE_URL", nullable = false)
    var imageUrl: String,

    @Column(name = "SKU_ID", nullable = false)
    var skuId: Long

)