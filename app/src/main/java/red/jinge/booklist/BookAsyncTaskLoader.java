package red.jinge.booklist;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Seeyon on 2017-11-29.
 */

public class BookAsyncTaskLoader extends AsyncTaskLoader<List<Book>> {
    String mRequestUrl;

    public BookAsyncTaskLoader(Context context, String requestUrl) {
        super(context);
        mRequestUrl = requestUrl;
    }

    @Override
    public List<Book> loadInBackground() {
        return QueryUtil.fetchBookList(mRequestUrl);
    }

    public void setmRequestUrl(String mRequestUrl) {
        this.mRequestUrl = mRequestUrl;
    }
}
