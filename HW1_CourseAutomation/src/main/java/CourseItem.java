/**
 * Created by Hasan MEN on 20.02.2016.
 * This class will use storage course item like book,url,pdf in course class
 * I made it abstruct and added common function implementations here
 *
 */
public abstract class CourseItem implements CourseAddable {

    private String name;

    /**
     * This method returns name of course item
     * @return name of course
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets name of course items
     * @param name new name of course item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constuctor for abstruct class, will invoke from childs
     * @param name
     */
    public CourseItem(String name) {
        this.name = name;
    }

    /**
     * This function must be implemented from child class
     * Method will update courseItem object with new ona
     * @param newCourseItem new course item
     * @return status of updating
     */
    public abstract boolean updateCourseItem(CourseAddable newCourseItem);
}
