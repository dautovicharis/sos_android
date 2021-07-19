package dh.sos

import android.app.Application
import androidx.room.Room
import dh.sos.database.AppDatabase

class App: Application() {
    companion object DatabaseSetup {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =   Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "db_users"
        ).build()
    }
}