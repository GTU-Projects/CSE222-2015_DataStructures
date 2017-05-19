/**
 * Created by Hasan MEN on 20.02.2016.
 */

/**
 * This class created for add assingment in the course and store them.
 */
public class Assignment extends CourseItem {
    private String deadLine; // dead line
    private String lateDeadLine; // late dead line
    private int asssingmentSize; // size of file which will add
    private static final int maxAssignmentSize = 50;

    /**
     * ASSIGNMENT CONSTRUCTOR
     * @param name name of assignment
     * @param deadLine deadline of assingment
     * @param lateDeadLine late deadline of assignment
     * @param asssingmentSize assignment size, max size = 50;
     */
    public Assignment(String name, String deadLine, String lateDeadLine, int asssingmentSize) {
        super(name);
        this.deadLine = deadLine;
        this.lateDeadLine = lateDeadLine;
        this.asssingmentSize = asssingmentSize;
    }

    /**
     * This functions return dead line
     * @return String of dead line
     */
    public String getDeadLine() {
        return deadLine;
    }

    /**
     * This function returns late dead line
     * @return string of late dead line
     */
    public String getLateDeadLine() {
        return lateDeadLine;
    }

    /**
     * This method return assignment size
     * @return assignment size
     */
    public int getAsssingmentSize() {
        return asssingmentSize;
    }

    /**
     * This function sets dead line
     * @param deadLine new deadline
     */
    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    /**
     * This method sets late dead line
     * @param lateDeadLine new late dead line
     */
    public void setLateDeadLine(String lateDeadLine) {
        this.lateDeadLine = lateDeadLine;
    }

    /**
     * This method sets new assignment size
     * @param asssingmentSize new assignment size
     */
    private void setAsssingmentSize(int asssingmentSize) {
        this.asssingmentSize = asssingmentSize;
    }

    /**
     * This method take valid course item(Assignment) and changes with old assignment;
     * @param newCourseItem new course item to update
     * @return status of updating
     */
    @Override
    public boolean updateCourseItem(CourseAddable newCourseItem) {

        if (newCourseItem instanceof Assignment) {
            Assignment tempAssingment = (Assignment) newCourseItem;

            setName(tempAssingment.getName());
            setDeadLine(tempAssingment.getDeadLine());
            setLateDeadLine(tempAssingment.getLateDeadLine());
            setAsssingmentSize(tempAssingment.getAsssingmentSize());

            return true;
        } else return false;

    }
}
