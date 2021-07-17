package hapax.app.simple

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

class CachedCall<T> (
    private var stored : Response<T>? = null,
    private var error : Throwable? = null,
    private val call : Call<T>,
    private val queue : Queue<Callback<T>> = ConcurrentLinkedQueue()
) : Call<T> {

    init {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                stored = response
                run()
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                error = t
                run()
            }
        })
    }

    fun run() {
        while(true) {
            val callback = queue.poll() ?: break

            when {
                stored != null -> callback.onResponse(call, stored!!)
                error != null -> callback.onFailure(call, error!!)
            }
        }
    }

    override fun enqueue(callback: Callback<T>) {
        callback.apply {
            when {
                stored != null -> onResponse(call, stored!!)
                error != null -> onFailure(call, error!!)
                !call.isExecuted -> call.enqueue(this)
                else -> queue.add(this)
            }
        }
    }

    override fun clone(): Call<T> {
        return call.clone()
    }

    override fun execute(): Response<T> {
        return call.execute()
    }

    override fun isExecuted(): Boolean {
        return call.isExecuted
    }

    override fun cancel() {
        return call.cancel()
    }

    override fun isCanceled(): Boolean {
        return call.isCanceled
    }

    override fun request(): Request {
        return call.request()
    }

    override fun timeout(): Timeout {
        return call.timeout()
    }
}