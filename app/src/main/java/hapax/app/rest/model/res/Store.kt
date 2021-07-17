package hapax.app.rest.model.res

class Store (
    val _id : String,
    val name : String,
    val svgURI : String?,
    val svgWidth : Int?,
    val svgHeight : Int?,
    val products : List<Product>?
)