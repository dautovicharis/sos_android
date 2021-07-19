package dh.sos.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dh.sos.entities.User

@Dao
interface MyModelDao {

    @Insert
    suspend fun insertUser(users: User)

    @Transaction
    @Query("Select * from User")
    suspend fun getUsers(): List<User>?
}