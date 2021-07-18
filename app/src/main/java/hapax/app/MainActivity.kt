package hapax.app

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import hapax.app.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_stores.view.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                setTheme(R.style.Theme_Hapax)
            }
        }, 1000)
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setSupportActionBar(includeLayout.menu_toolbar)

            val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, includeLayout.menu_toolbar, R.string.open, R.string.close)
            toggle.isDrawerIndicatorEnabled = true
            toggle.isDrawerSlideAnimationEnabled = true
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
        }
    }
}