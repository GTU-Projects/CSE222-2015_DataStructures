/**
 * Created by Hasan MEN on 20.02.2016.
 *
 * This class created to store documents in course.
 * Extended from coruse item abstruct class
 * BOOKS, PDF will extended from this class
 */
public abstract class Document extends CourseItem {

    // document size
    private int size;

    // CONSTRUCTOR
    public Document(String name, int size) {
        super(name);
        this.size = size;
    }
    /**
     * This functtion returns documetns size
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * This function must be implemented from child class
     * Method will update courseItem object with new ona
     * @param newCourseItem new course item
     * @return status of updating
     */
    @Override
    public abstract boolean updateCourseItem(CourseAddable newCourseItem);
}
