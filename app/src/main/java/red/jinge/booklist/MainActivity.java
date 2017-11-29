package red.jinge.booklist;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    ListView listViewBooks;
    EditText searchViewKeyword;
    TextView textViewEmpty;
    ProgressBar progressBarLoading;
    BookAdapter bookAdapter;

    private static final String DOUBAN_BOOK_URL = "https://api.douban.com/v2/book/search?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewBooks = findViewById(R.id.list_view_books);
        searchViewKeyword = findViewById(R.id.edit_text_keyword);
        textViewEmpty = findViewById(R.id.text_view_empty);
        progressBarLoading = findViewById(R.id.progress_bar_loading);
        progressBarLoading.setVisibility(View.GONE);

        // 初始化listview：刚开始是空的
        bookAdapter = new BookAdapter(this, new ArrayList<Book>());
        listViewBooks.setAdapter(bookAdapter);
        listViewBooks.setEmptyView(textViewEmpty);
        listViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book earthquake = (Book) parent.getItemAtPosition(position);
                Uri webpage = Uri.parse(earthquake.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


        // check internet connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            progressBarLoading.setVisibility(View.GONE);
            textViewEmpty.setText(R.string.no_network_connection);
        }
    }

    public void startSearch(View view) {
        progressBarLoading.setVisibility(View.VISIBLE);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        String keyword = searchViewKeyword.getText().toString();
        return new BookAsyncTaskLoader(this, DOUBAN_BOOK_URL + keyword);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        updateUI(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        updateUI(new ArrayList<Book>());
    }

    /**
     * 更新UI
     * @param books earthquake list from network
     */
    private void updateUI(List<Book> books) {
        progressBarLoading.setVisibility(View.GONE);
        textViewEmpty.setText(R.string.no_result);
        bookAdapter.clear();

        if (books != null && books.size() != 0) {
            bookAdapter.addAll(books);
        }
    }
}
