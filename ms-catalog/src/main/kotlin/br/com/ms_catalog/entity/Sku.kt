package br.com.ms_catalog.entity

import jakarta.persistence.*

@Entity
@Table(name = "SKU", schema = "MS_CATALOG_SCH")
class Sku (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,

    @Column(name = "PRICE", nullable = false)
    var price : Double,

    @Column(name = "QUANTITY", nullable = false)
    var quantity : Int,

    @Column(name = "COLOR", nullable = false)
    var color : String,

    @Column(name = "SIZE", nullable = false)
    var size : String,

    @Column(name = "HEIGHT", nullable = false)
    var height : Double,

    @Column(name = "WIDTH", nullable = false)
    var width : Double,

    @Column(name = "PRODUCT_ID", nullable = false)
    var productId : Long
)