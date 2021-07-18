package hapax.app

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caverock.androidsvg.SVG
import hapax.app.adapter.ProductAdapter
import hapax.app.databinding.FragmentProductsBinding
import hapax.app.rest.RESTService
import hapax.app.rest.model.req.StoreId
import hapax.app.rest.model.req.SvgId
import hapax.app.rest.model.res.Product
import hapax.app.util.callback
import hapax.app.util.listener
import hapax.app.util.search
import kotlin.math.ceil


class FragmentProducts: Fragment(R.layout.fragment_products) {
    private var svg : SVG? = null
    private lateinit var svgView : ImageView
    private var prevSearch : String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        svgView = view.findViewById(R.id.image)

        val name = arguments?.getString("store")!!
        val adapter = ProductAdapter(this)

        FragmentProductsBinding.bind(view).apply {
            rvProducts.adapter = adapter
            rvProducts.layoutManager = LinearLayoutManager(context)

            RESTService.serv.getStores(StoreId(name)).enqueue(callback { store ->
                RESTService.serv.getSVG(SvgId(store.svgURI)).enqueue(callback { body ->
                    svg = SVG.getFromInputStream(body.byteStream())
                })

                searchView.setOnQueryTextListener(listener { search ->
                    val results = store.products.search(search, Product::name).sortedByDescending(Product::stars)
                    if(search != prevSearch) hideSVG()

                    adapter.search( results )
                    rvProducts.setPadding(0, if (results.isEmpty()) 0 else 40, 0, 0)
                    prevSearch = search
                })
            })
        }
    }

    private val productPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = resources.getColor(R.color.red, context?.theme)
            style = Paint.Style.FILL
            textSize = 20f
        }
    }

    fun displaySVG(product : Product) {
        val svg = svg ?: return

        val bitMap = Bitmap.createBitmap(
            ceil(svg.documentWidth).toInt(),
            ceil(svg.documentHeight).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitMap)
        svg.renderToCanvas(canvas)
        //canvas.drawText(product.name, product.x.toFloat() - 5, product.y.toFloat() - 10, productPaint)
        canvas.drawCircle(product.x.toFloat(), product.y.toFloat(), 20f, productPaint)
        svgView.setImageBitmap(bitMap)
    }

    private fun hideSVG() {
        svgView.setImageBitmap(null)
    }
}

