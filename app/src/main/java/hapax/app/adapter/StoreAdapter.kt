package hapax.app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hapax.app.ProductFragment
import hapax.app.R
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
        val name = stores[position]
        val view = holder.itemView
        view.setOnClickListener {
            val storeIntent = Intent(view.context, ProductFragment::class.java).putExtra("store", name)
            view.context.startActivity(storeIntent)
        }
        RcStoreBinding.bind(view).name.text = name
    }

    override fun getItemCount(): Int {
        return stores.size
    }
}