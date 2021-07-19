package dh.sos.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val username: String,
    val email: String,

    val address: Address,

    val phone: String,
    val website: String,
    @Embedded val company: Company
)
