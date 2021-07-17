package hapax.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hapax.app.adapter.StoreAdapter
import hapax.app.databinding.SearchLayoutBinding
import hapax.app.rest.RESTService
import hapax.app.util.*
import java.util.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timer().schedule(object: TimerTask() {
            override fun run() {
                setTheme(R.style.Theme_Hapax)
            }
        }, 1000)

        super.onCreate(savedInstanceState)

        val adapter = StoreAdapter()
        SearchLayoutBinding.inflate(layoutInflater).apply {
            setContentView(root)

            rvStores.adapter = adapter
            rvStores.layoutManager = LinearLayoutManager(this@SearchActivity)

            RESTService.serv.getStores().enqueue(callback { stores ->
                searchView.setOnQueryTextListener(listener { search ->
                    val results = stores.search(search) { it }
                    adapter.search( results )
                    rvStores.setPadding(0, if (results.isEmpty()) 0 else 40, 0, 0)
                })
            })
        }
    }
}