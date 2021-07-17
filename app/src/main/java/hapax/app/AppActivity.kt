package hapax.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hapax.app.adapter.ProductAdapter
import hapax.app.databinding.AppLayoutBinding
import hapax.app.rest.RESTService
import hapax.app.rest.model.req.StoreId
import hapax.app.rest.model.res.Product
import hapax.app.util.*

class AppActivity: AppCompatActivity() {
    lateinit var name : String
    val store by lazy { CachedCall(call = RESTService.serv.getStores(StoreId(name))) }
    private val adapter = ProductAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name = intent.extras?.getString("store")!!

        AppLayoutBinding.inflate(layoutInflater).apply {
            setContentView(root)
            rvProducts.adapter = adapter
            rvProducts.layoutManager = LinearLayoutManager(this@AppActivity)

            store.enqueue(callback { store ->
                searchView.setOnQueryTextListener(listener { search ->
                    val results = store.products.search(search, Product::name)
                    adapter.search( results )
                    rvProducts.setPadding(0, if (results.isEmpty()) 0 else 40, 0, 0)
                    true
                })
            })
        }
    }
}

