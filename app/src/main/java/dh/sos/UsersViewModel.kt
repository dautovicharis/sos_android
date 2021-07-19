package dh.sos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dh.sos.dao.MyModelDao
import dh.sos.entities.User
import kotlinx.coroutines.launch

class UsersViewModel(private val dataSource: MyModelDao) :ViewModel() {

    fun addUser(user: User) {
        viewModelScope.launch {
            dataSource.insert(user)
        }
    }
}