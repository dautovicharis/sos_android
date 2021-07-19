package dh.sos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dh.sos.dao.MyModelDao

class ViewModelFactory(private val dataSource: MyModelDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}