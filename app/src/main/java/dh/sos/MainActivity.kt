package dh.sos

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dh.sos.database.AppDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders
            .of(this, ViewModelFactory(AppDatabase.getDatabase(this).userDao()))
            .get(UsersViewModel::class.java)

        findViewById<Button>(R.id.btnInsert).setOnClickListener {
            viewModel.addUser()
        }

        findViewById<Button>(R.id.btnGet).setOnClickListener {
            viewModel.getUsers()
        }
    }
}