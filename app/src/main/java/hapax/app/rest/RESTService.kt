package hapax.app.rest

import hapax.app.rest.model.Store
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Streaming

interface RESTService {
    companion object Factory {
        private fun create() : RESTService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rest.levansj01.me")
                .build()

            return retrofit.create(RESTService::class.java)
        }

        val service by lazy {
            create()
        }
    }

    @Headers("Content-Type: application/json")
    @POST("stores")
    fun getStore(@Body name : String ): Call<Store>

    @Headers("Content-Type: application/json")
    @POST("svg")
    @Streaming
    fun getSVG(@Body name : String): Call<ResponseBody>
}