package red.jinge.booklist;

/**
 * Created by Seeyon on 2017-11-29.
 */

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mCoverImage;
    private String mUrl;

    public Book(String mTitle, String mAuthor, String mCoverImage, String mUrl) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mCoverImage = mCoverImage;
        this.mUrl = mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getCoverImage() {
        return mCoverImage;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mCoverImage='" + mCoverImage + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}
