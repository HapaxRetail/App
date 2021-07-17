package hapax.app.simple;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface SimpleCallback<T> extends Callback<T> {
    default void onResponse(@NotNull Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            T value = response.body();
            if(value != null) {
                call(value);
            }
        }
    }

    default void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {}

    void call(@NotNull T value);
}
