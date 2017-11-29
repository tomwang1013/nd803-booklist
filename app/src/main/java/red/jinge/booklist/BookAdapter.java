package red.jinge.booklist;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Seeyon on 2017-11-29.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 刚开始渲染的时候没有可重用的view，需要自己创建
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.book_item, parent, false);
        }

        Book book = getItem(position);
        ImageView imageCover = convertView.findViewById(R.id.image_view_cover);
        TextView textTitle = convertView.findViewById(R.id.text_view_title);
        TextView textAuthor = convertView.findViewById(R.id.text_view_author);

        Log.i("BookAdapter: ", book.toString());

        textTitle.setText(book.getTitle());
        textAuthor.setText(book.getAuthor());

        new ImageDownloadAsyncTask(imageCover).execute(book.getCoverImage());

        return convertView;
    }
}
