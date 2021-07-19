package dh.sos.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Company (
    val companyName: String,
    val catchPhrase: String,
    val bs: String
)