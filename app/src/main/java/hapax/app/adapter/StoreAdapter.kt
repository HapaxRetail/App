package hapax.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import hapax.app.FragmentProducts
import hapax.app.FragmentStores
import hapax.app.R
import hapax.app.databinding.RcStoreBinding

class StoreAdapter (private val parent: FragmentStores) :
    RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    private var stores: List<String> = emptyList()

    class StoreViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rc_store,
                parent,
                false
            )
        )
    }

    fun search(search : List<String>) {
        stores = search
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val name = stores[position]
        val view = holder.itemView
        RcStoreBinding.bind(view).name.text = name
        view.setOnClickListener {
            parent.requireActivity().supportFragmentManager.commit {
                replace<FragmentProducts>(R.id.include_layout, args = bundleOf("store" to name))
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    override fun getItemCount(): Int {
        return stores.size
    }
}