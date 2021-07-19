package dh.sos.entities

import androidx.room.*


data class Address (
    val street: String?= null,
    val suite: String?= null,
    val city: String?= null,
    val zipcode: String?= null,
    @Embedded val geo: Geo? = null
)