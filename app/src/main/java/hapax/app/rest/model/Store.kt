package hapax.app.rest.model

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.ResponseBody

class Store (
    val _id : String,
    val name : String,
    val svgURI : String,
    val svgWidth : Int,
    val svgHeight : Int,
    val products : List<Product?>
) {
    constructor(obj: JsonObject) : this(
        obj.get("_id").asString,
        obj.get("name").asString,
        obj.get("svgURI").asString,
        obj.get("svgWidth").asInt,
        obj.get("svgHeight").asInt,
        obj.get("products").asJsonArray.map(::Product)
    )

    constructor(res: ResponseBody) : this(GsonBuilder().create().fromJson(res.string(), JsonObject::class.java))
}