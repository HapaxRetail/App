package hapax.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hapax.app.adapter.StoreAdapter
import hapax.app.databinding.FragmentStoresBinding
import hapax.app.rest.RESTService
import hapax.app.util.callback
import hapax.app.util.listener
import hapax.app.util.search

class FragmentStores : Fragment(R.layout.fragment_stores) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = StoreAdapter()
        FragmentStoresBinding.bind(view).apply {
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