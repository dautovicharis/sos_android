package dh.sos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dh.sos.dao.MyModelDao
import dh.sos.entities.*
import kotlinx.coroutines.launch
import java.lang.Exception

class UsersViewModel(private val dataSource: MyModelDao) :ViewModel() {

    fun addUser(user: User = getTestUser()) {
        viewModelScope.launch {
            dataSource.insertUser(user)
        }
    }

    fun getUsers () {
        viewModelScope.launch {
            try {
                val result = dataSource.getUsers()
            } catch (exception: Exception) {
                Log.d("Error:", exception.toString())
            }
        }
    }

    private fun getTestUser(): User {
        return User(
            name = randomString(),
            username = randomString(),
            email = randomString(),
            address = Address(city = "Sarajevo"),
            phone = "-",
            website = "-",
            company = Company(
                companyName = randomString(),
                catchPhrase = randomString(),
                bs = randomString()
            )
        )
    }

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun randomString(): String {
        return (1..7)
            .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}