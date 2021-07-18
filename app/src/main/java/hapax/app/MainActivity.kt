package hapax.app

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import hapax.app.databinding.ActivityMainBinding
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
            val toolbar = findViewById<Toolbar>(R.id.menuToolbar)
            toolbar.title = ""
            setSupportActionBar(toolbar)

            val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar, R.string.open, R.string.close)
            toggle.isDrawerIndicatorEnabled = true
            toggle.isDrawerSlideAnimationEnabled = true
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
        }
    }
}