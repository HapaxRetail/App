package hapax.app

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import hapax.app.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                setTheme(R.style.Theme_Hapax)
            }
        }, 1000)
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            drawer = drawerLayout
            setContentView(root)
            val toolbar = findViewById<Toolbar>(R.id.menuToolbar).apply { title = "" }
            setSupportActionBar(toolbar)

            val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar, R.string.open, R.string.close)
            toggle.isDrawerIndicatorEnabled = true
            toggle.isDrawerSlideAnimationEnabled = true
            drawer.addDrawerListener(toggle)
            toggle.syncState()

            navMenu.setNavigationItemSelectedListener(this@MainActivity)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val clazz = when(item.itemId) {
            R.id.nav_findStore -> FragmentStores::class.java
            R.id.nav_profile -> FragmentProfile::class.java
            else -> return super.onOptionsItemSelected(item)
        }
        supportFragmentManager.commit {
            replace(R.id.include_layout, clazz, null, null)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        drawer.closeDrawers()

        val service = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        service.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
        return true
    }
}