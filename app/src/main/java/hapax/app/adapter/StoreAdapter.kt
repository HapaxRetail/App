package hapax.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import hapax.app.FragmentProducts
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
        RcStoreBinding.bind(view).name.text = name
        view.setOnClickListener {
            (view.context as? FragmentActivity
                ?: return@setOnClickListener).supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FragmentProducts>(R.id.include_layout, args = bundleOf("store" to name))
            }
        }
    }

    override fun getItemCount(): Int {
        return stores.size
    }
}