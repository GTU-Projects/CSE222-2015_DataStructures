/**
 * Created by Hasan MEN on 20.02.2016.
 *
 * This class can store books. Every books has name, author name, release date and size
 */
public class Book extends Document {
    private String releaseDate; // release date for book
    private String authorName; // author name of book

    /**
     * BOOK CONSTRUCTOR
     * @param name name of book
     * @param authorName author name of book
     * @param releaseDate release date
     * @param size size of book
     */
    public Book(String name, String authorName,String releaseDate, int size) {
        super(name, size);
        this.authorName = authorName;
        this.releaseDate = releaseDate;
    }

    /**
     * This method retusn book release date
     * @return String of release date
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * This method sets book release date
     * @param releaseDatea string release date
     */
    public void setReleaseDate(String releaseDatea) {
        this.releaseDate = releaseDate;
    }

    /**
     * This method returns author name
     * @return string of author name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * This method sets author name of book
     * @param authorName author name of book
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    /**
     * This method was overrided from course addable interface , will use for update books with new one
     * @param newCourseItem new book to update with old
     * @return status of updating
     */
    @Override
    public boolean updateCourseItem(CourseAddable newCourseItem) {
        if (newCourseItem instanceof Book) {
            Book tempBook = (Book) newCourseItem;
            setName(tempBook.getName()); // isimlerini ata
            setAuthorName(tempBook.getAuthorName());
            setReleaseDate(tempBook.getReleaseDate());
            return true;
        } else return false;
    }

    /**
     * This method overrided from object class
     * @return book information
     */
    @Override
    public String toString() {
        return String.format("BOOK -> Name : " + getName() + " - AuthorName : " + getAuthorName() + " - Size : " + getSize());
    }
}
