import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasan MEN on 20.02.2016.
 * This class created for storage course like real moodle course.
 * Every course can have name and course id
 * Courses has course teacher , course students , course items
 * When s student enrol the course, the teacher will accept enrolment and add him/her to course student list
 *
 */
public class Course{
    private static int COURSE_ID = 0;
    private String name;
    private int courseID;
    private List<User> courseTeachers = new ArrayList<>(); // Course teachers
    private List<User> courseStudents = new ArrayList<>(); // course students
    public List<User> courseRequests = new ArrayList<>(); // course enrolment list
    private List<CourseItem> courseItems = new ArrayList<>(); // course items


    /**
     * COPY CONSTRUCTOR
     * @param copy new course object for copy
     */
    public Course(Course copy){
        this.name = copy.name;
        this.courseID = copy.getCourseID();
        this.courseTeachers = new ArrayList<>(copy.getCourseTeachers());
        this.courseStudents = new ArrayList<>(copy.getCourseStudents());
        this.courseRequests = new ArrayList<>(copy.getCourseRequests());
        this.courseItems = new ArrayList<>(copy.getCourseItems());
    }

    /**
     * CONSTRUCTOR
     * @param name new name of course
     */
    public Course(String name) {
        this.name = name;
        ++COURSE_ID;
        courseID = COURSE_ID;
    }

    /**
     * This method gets name of course
     * @return course name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets course items lists
     * It's public but not dangerous.
     * @return list of course items
     */
    public List<CourseItem> getCourseItems() {
        return courseItems;
    }

    /**
     * This method sets name of course
     * @param name new name of couse
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns course ıd
     * @return course ıd
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * This method sets course ıd with new one
     * @param courseID new course id
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * This methods returns lists of course teachers. Teacher in list are referance of real system teachers
     * @return list of corurse teacher
     */
    public List<User> getCourseTeachers() {
        return courseTeachers;
    }


    /**
     * This methods returns lists of course students. Students in list are referance of real system teachers
     * @return list of corurse students
     */
    public List<User> getCourseStudents() {
        return courseStudents;
    }

    /**
     * This method gets list of course enrolments. Teacher can manipulate.
     * @return list of course requests
     */
    public List<User> getCourseRequests() {
        return courseRequests;
    }

    /**
     * This methods search teacher in course and returns him/her index
     * @param teacher teacher for search
     * @return index of teacher
     */
    public int getTeacherIndex(User teacher){
        return getCourseTeachers().indexOf(teacher);
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("Course : " + getName());
        for (int i = 0; i < getCourseTeachers().size(); ++i) {
            str.append("\n\t\t" + getCourseTeachers().get(i));
        }

        return str.toString();
    }


    /**
     * This method takes student and add him/her to course requests lists
     * @param student student reference to add list
     * @return status of adding
     */
    public boolean joinCourse(Student student) {
        // take copy of user with call by value and add in request list
        if (student != null && !courseRequests.contains(student)) {
            courseRequests.add(student);
            return true;
        } else return false;
    }

    /**
     * This method compares two course object
     * @param o course object
     * @return status of equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return name != null ? name.equals(course.name) : course.name == null;
    }

    /**
     * This methods takes course item and adds in the course item lists.
     * @param item new course item
     * @return status of adding
     */
    public boolean addCourseItem(CourseItem item){
        if(null != item){
            courseItems.add(item);
            return true;
        }
        return false;
    }

    /**
     * This method returs course item in list as a string
     * @return list of course items as a string
     */
    public String showItems(){
        StringBuilder strBldr = new StringBuilder();

        strBldr.append(getName() + " Couse Items : \n");
        for(CourseItem itr : courseItems){
            strBldr.append("\t"+itr+"\n");
        }
        return strBldr.toString();
    }

}
