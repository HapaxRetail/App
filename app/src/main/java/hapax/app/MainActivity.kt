package hapax.app

import android.content.Intent
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import hapax.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val loading: Drawable by lazy { get(R.drawable.loading_page) }
    val nav: Drawable by lazy { get(R.drawable.navigation_panel) }
    val search: Drawable by lazy { get(R.drawable.search_page_closed) }
    val searchList: Drawable by lazy { get(R.drawable.search_page_open) }
    val toggle: Drawable by lazy { get(R.drawable.toggle_right) }

    fun get(@DrawableRes id : Int) : Drawable {
        return ResourcesCompat.getDrawable(resources, id, theme)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}

