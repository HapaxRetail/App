package hapax.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hapax.app.adapter.ProductAdapter
import hapax.app.databinding.AppLayoutBinding
import hapax.app.rest.RESTService
import hapax.app.rest.model.req.StoreId
import hapax.app.simple.CachedCall
import hapax.app.simple.SimpleCallback
import hapax.app.simple.SimpleTextListener

class AppActivity: AppCompatActivity() {
    lateinit var name : String
    val store by lazy { CachedCall(call = RESTService.serv.getStores(StoreId(name))) }
    private val adapter by lazy { ProductAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name = intent.extras?.getString("store")!!

        AppLayoutBinding.inflate(layoutInflater).apply {
            setContentView(root)
            rvProducts.adapter = adapter
            rvProducts.layoutManager = LinearLayoutManager(this@AppActivity)

            store.enqueue(SimpleCallback { store ->
                searchView.setOnQueryTextListener(SimpleTextListener { search ->
                    val results =
                        if(search.isEmpty()) emptyList()
                        else store.products.filter { product -> product.name.startsWith(search, false) }

                    adapter.search( results )
                    rvProducts.setPadding(0, if (results.isEmpty()) 0 else 40, 0, 0)

                    true
                })
            })
        }
    }
}

