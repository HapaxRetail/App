package hapax.app.rest.model

class Store (
    val name : String,
    val svgURI : String,
    val svgWidth : Int,
    val svgHeight : Int,
    val products : Map<String, Product>
)