package hapax.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hapax.app.R
import hapax.app.databinding.RcStoreBinding
import hapax.app.rest.model.res.Product

class ProductAdapter (private var products: List<Product> = emptyList()) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rc_store,
                parent,
                false
            )
        )
    }

    fun search(search : List<Product>) {
        products = search

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        val view = holder.itemView
        view.setOnClickListener {
            // TODO: Clicking product
        }
        RcStoreBinding.bind(view).tvSearchTitle.text = product.name
    }

    override fun getItemCount(): Int {
        return products.size
    }
}