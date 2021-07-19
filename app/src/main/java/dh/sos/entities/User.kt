package dh.sos.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    val name: String,
    val username: String,
    val email: String,
    @Embedded val address: Address,
    val phone: String,
    val website: String,
    @Embedded val company: Company
)
