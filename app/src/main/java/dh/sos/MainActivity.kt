package dh.sos

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dh.sos.entities.Address
import dh.sos.entities.Company
import dh.sos.entities.Geo
import dh.sos.entities.User

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders
            .of(this, ViewModelFactory(App.database!!.userDao()))
            .get(UsersViewModel::class.java)


        findViewById<Button>(R.id.btnInsert).setOnClickListener {
            viewModel.addUser(getTestObject())
        }
    }

    private fun getTestObject(): User {
        return User(
            name = randomString(),
            username = randomString(),
            email = randomString(),
            address = Address(
                street = randomString(),
                suite = randomString(),
                city = randomString(),
                zipcode = "71000",
                geo = Geo(lat = "-", lng = "-")
            ),
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