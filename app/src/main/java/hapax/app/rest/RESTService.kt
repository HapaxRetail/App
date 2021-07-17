package hapax.app.rest

import hapax.app.rest.model.req.StoreId
import hapax.app.rest.model.req.SvgId
import hapax.app.rest.model.res.Store
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Streaming

interface RESTService {
    companion object Factory {
        val serv: RESTService by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rest.levansj01.me")
                .build().create(RESTService::class.java)
        }
    }

    @POST("stores")
    fun getStores(@Body store : StoreId ): Call<Store>

    @GET("stores")
    fun getStores(): Call<List<String>>

    @POST("svg")
    @Streaming
    fun getSVG(@Body svg : SvgId): Call<ResponseBody>
}