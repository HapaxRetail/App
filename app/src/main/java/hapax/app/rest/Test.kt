package hapax.app.rest

import hapax.app.rest.model.Store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun main(args : Array<String>) {
    RESTService.service.getStore("next").enqueue(object : Callback<Store> {
        override fun onResponse(call: Call<Store>, response: Response<Store>) {
            println(response.body()?.name);
        }

        override fun onFailure(call: Call<Store>, t: Throwable) {
            println("Error")
        }
    })
}