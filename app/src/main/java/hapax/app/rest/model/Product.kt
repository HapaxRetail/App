package hapax.app.rest.model

import com.google.gson.JsonElement
import com.google.gson.JsonObject

class Product (
    val name : String,
    val price : Double,
    val x : Int,
    val y : Int
) {
    constructor(obj : JsonObject) : this(
        obj.get("name").asString,
        obj.get("price").asDouble,
        obj.get("x").asInt,
        obj.get("y").asInt
    )

    constructor(elem : JsonElement) : this(elem.asJsonObject)
}

