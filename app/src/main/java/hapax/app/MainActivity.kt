package hapax.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import hapax.app.databinding.ActivityMainBinding
import hapax.app.databinding.SearchLayoutBinding
import java.util.*

//import kotlinx.android.synthetic.main.search_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                setTheme(R.style.Theme_Hapax)
            }
        }, 1000)
        super.onCreate(savedInstanceState)

        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val searchBinding = SearchLayoutBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)
        setSupportActionBar(searchBinding.menuToolbar)

        val toggle = ActionBarDrawerToggle(this, mainBinding.drawerLayout, searchBinding.menuToolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        toggle.isDrawerSlideAnimationEnabled = true
        mainBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}