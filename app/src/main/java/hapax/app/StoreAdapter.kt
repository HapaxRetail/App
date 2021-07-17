package hapax.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hapax.app.databinding.RcStoreBinding

class StoreAdapter (private var stores: List<String> = emptyList()) :
    RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

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
        RcStoreBinding.bind(holder.itemView).tvSearchTitle.text = stores[position]
    }

    override fun getItemCount(): Int {
        return stores.size
    }
}