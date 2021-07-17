package hapax.app.util

import android.widget.SearchView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> callback(function: (T) -> Unit) : Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            function.invoke(response.body() ?: return)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {}
    }
}

fun listener(function: (String) -> Boolean) : SearchView.OnQueryTextListener {
    return object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(search: String): Boolean {
            return function.invoke(search)
        }

        override fun onQueryTextChange(search: String): Boolean {
            return function.invoke(search)
        }
    }
}

fun <T> List<T>.search(search : String, function: (T) -> String) : List<T> {
    if(search.isEmpty()) return emptyList()

    return filter { item -> function.invoke(item).contains(search, true) }
        .sortedBy { item -> if(function.invoke(item).startsWith(search, true)) 0 else 1 }
}