/**
 * Created by Hasan MEN on 20.02.2016.
 *
 *
 * This interface will use abstruct course item class
 * every course item must be updatable.
 *
 */
public interface CourseAddable {

    /**
     * This function will take new object and change with old one
     * @param newCourseItem new course item
     * @return status of changing
     */
    boolean updateCourseItem(CourseAddable newCourseItem);

}
