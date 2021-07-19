package dh.sos.dao

import androidx.room.Dao
import androidx.room.Insert
import dh.sos.entities.User

@Dao
interface MyModelDao {

    @Insert
    suspend fun insert(vararg users: User)
}