package hapax.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hapax.app.databinding.SearchLayoutBinding
import hapax.app.rest.RESTService
import hapax.app.simple.CachedCall
import hapax.app.simple.SimpleCallback
import hapax.app.simple.SimpleTextListener
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

        SearchLayoutBinding.inflate(layoutInflater).apply {
            setContentView(root)

            rvStores.adapter = adapter
            rvStores.layoutManager = LinearLayoutManager(this@SearchActivity)
            searchView.setOnQueryTextListener(SimpleTextListener { search ->
                storeList.enqueue(SimpleCallback { stores ->
                    val results =
                        if(search.isEmpty()) emptyList()
                        else stores.filter { name -> name.startsWith(search, true) }

                    adapter.search( results )
                    rvStores.setPadding(0, if (results.isEmpty()) 0 else 40, 0, 0)
                })
                true
            })
        }
    }

}