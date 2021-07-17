package hapax.app.rest

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hapax.app.rest.model.Store
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Streaming
import java.util.function.Consumer

@RequiresApi(Build.VERSION_CODES.N)
interface RESTService {
    companion object Factory {
        private fun create() : RESTService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://rest.levansj01.me")
                .build()

            return retrofit.create(RESTService::class.java)
        }

        private val service by lazy {
            create()
        }

        fun getStore(name : String, consumer : Consumer<Store?>) {
            service.getStore(JsonObject().build("store", name).toHTTP()).enqueue(
                object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        consumer.accept(Store(response.body()!!))
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        consumer.accept(null)
                    }
                }
            )
        }

        private fun JsonObject.build (k: String, v : String) : JsonObject {
            addProperty(k, v)
            return this
        }

        private fun JsonObject.toHTTP() : RequestBody {
            return toString().toRequestBody("application/json".toMediaTypeOrNull())
        }
    }

    @POST("stores")
    fun getStore(@Body store : RequestBody ): Call<ResponseBody>

    @POST("svg")
    @Streaming
    fun getSVG(@Body svgURI : RequestBody): Call<ResponseBody>
}
