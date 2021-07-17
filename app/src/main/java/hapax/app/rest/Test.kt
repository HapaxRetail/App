package hapax.app.rest

import java.util.function.Consumer

fun main(array: Array<String>) {
    RESTService.getStore("next", Consumer { store ->
        if(store == null)
            println("No store found")
        else
            println("Name=${store.name} SVG:${store.svgURI}")
    } )
}