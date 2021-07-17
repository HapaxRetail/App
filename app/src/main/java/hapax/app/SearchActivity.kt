package hapax.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hapax.app.databinding.SearchLayoutBinding
import java.util.*

class SearchActivity : AppCompatActivity() {
    private val adapter by lazy { StoreAdapter() }
    private val storeList by lazy { CachedCall(call = RESTService.serv.getStores()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                setTheme(R.style.Theme_Hapax)
            }
        }, 1000)
        super.onCreate(savedInstanceState)

        val binding = SearchLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}