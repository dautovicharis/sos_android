package dh.sos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dh.sos.dao.MyModelDao
import dh.sos.entities.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): MyModelDao
}