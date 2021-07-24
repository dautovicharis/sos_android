package dh.sos

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.recyclerview.widget.LinearLayoutManager
import dh.sos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DrawerAdapter.DrawerAdapterCallback {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)

        //drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.mainFragment),
            binding.drawerLayout
        )
        //menu item click handle
        binding.navView.setupWithNavController(navController)

        // Setup action bar with nav controller
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Load drawer items
        loadDrawerItems(
            arrayOf(
                "Item 1",
                "Item 2",
                "Item 3"
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    private fun loadDrawerItems(items: Array<String>) {
        binding.rvDrawer.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DrawerAdapter(items, this@MainActivity)
        }
    }


    override fun onDrawerItemClick(value: String) {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        val direction = NavGraphDirections.refreshMainFragment(value)
        navController.navigate(direction)
    }
}
