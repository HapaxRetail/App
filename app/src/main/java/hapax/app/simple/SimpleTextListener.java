package hapax.app.simple;

import android.widget.SearchView;

public interface SimpleTextListener extends SearchView.OnQueryTextListener {
    default boolean onQueryTextSubmit(String search) {
        return change(search);
    }

    default boolean onQueryTextChange(String search) {
        return change(search);
    }

    boolean change(String search);
}
