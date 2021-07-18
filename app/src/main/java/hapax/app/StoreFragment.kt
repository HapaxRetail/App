package hapax.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hapax.app.adapter.StoreAdapter
import hapax.app.databinding.SearchLayoutBinding
import hapax.app.rest.RESTService
import hapax.app.util.*
import kotlinx.android.synthetic.main.search_layout.*
import java.util.*

class StoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.store_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = StoreAdapter()

        SearchLayoutBinding.inflate(layoutInflater).apply {
            rvStores.adapter = adapter
            rvStores.layoutManager = LinearLayoutManager(context)

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