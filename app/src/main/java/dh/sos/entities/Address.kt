package dh.sos.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Address (
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @Embedded val geo: Geo
)