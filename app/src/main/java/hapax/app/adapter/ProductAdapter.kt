package hapax.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hapax.app.R
import hapax.app.databinding.RcProductBinding
import hapax.app.rest.model.res.Product
import java.text.DecimalFormat

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var products: List<Product> = emptyList()
    private var product : Product? = null
    private val shoppingList = mutableSetOf<Product>()

    class ProductViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rc_product,
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
        RcProductBinding.bind(view).apply {
            name.text = product.name
            price.text = DecimalFormat("£0.00").format(product.price)
            inList.isChecked = shoppingList.contains(product)
            star1.setBackgroundColor(view.context.getColor(if(product.stars >= 1) R.color.yellow else R.color.white))
            star2.setBackgroundColor(view.context.getColor(if(product.stars >= 2) R.color.yellow else R.color.white))
            star3.setBackgroundColor(view.context.getColor(if(product.stars >= 3) R.color.yellow else R.color.white))
            inList.setOnClickListener { when {
                    shoppingList.remove(product) -> inList.isChecked = false
                    shoppingList.add(product) -> inList.isChecked = true
                } }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}