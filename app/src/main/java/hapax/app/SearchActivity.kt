package hapax.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hapax.app.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private val adapter by lazy { StoreAdapter() }
    private val storeList by lazy { CachedCall(call = RESTService.serv.getStores()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }
    }
}