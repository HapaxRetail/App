package hapax.app

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

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
        setContentView(R.layout.activity_main)
    }
}

