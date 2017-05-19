import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasan MEN on 21.02.2016.
 *
 * This class will store student informations.
 * Students will save courses which they take and all system courses to see available courses
 *
 */
public class Student extends User {


    private List<Course> takenCourses = new ArrayList<>();
    private List<Course> allSystemCourses = new ArrayList<>();
    private int studentID;
    public static final int NOT_FOUND = -1;

    /**
     * COPY CONSTRUCTOR
     * TAKES INDEPENDENT COPY
     * @param copyStd copy item
     */
    public Student(Student copyStd){
        super(copyStd);
        this.takenCourses = new ArrayList<>(copyStd.getTakenCourses());
        this.allSystemCourses = new ArrayList<>(copyStd.getAllSystemCourses());
        this.studentID = copyStd.getStudentID();
    }

    /**
     * Student constructor
     * Creates new student object and initlaize it
     * @param name name of student
     * @param surName surname of student
     * @param userName username of student
     * @param passWord password of student
     * @param eMail email address of student
     * @param studentID student id of student
     */
    public Student(String name, String surName, String userName, String passWord, String eMail, int studentID) {
        super(name, surName, userName, passWord, eMail);
        this.studentID = studentID;
    }

    /**
     * This fucntion returns student id
     * @return student id
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * This method will return list of taken courses.
     * @return list of taken courses
     */
    public List<Course> getTakenCourses() {
        return takenCourses;
    }

    /**
     * Get all system courses to see and enrol them
     * @return list of all system courses
     */
    public List<Course> getAllSystemCourses() {
        return allSystemCourses;
    }

    /**
     * This method sets list of system courses
     * @param allSystemCourses new list reference of system courses
     */
    public void setAllSystemCourses(List<Course> allSystemCourses) {
        this.allSystemCourses = allSystemCourses;
    }

    /**
     * This methods takes course and if course available in system course request membership. Then teacher must accept
     * request to see course details
     * @param course course for enrolment
     * @return status of enrolment
     */
    public boolean enrolCourse(Course course) {
        int courseIndex = takenCourses.indexOf(course);
        // o derse kayıtlı degilse
        if(NOT_FOUND == courseIndex ){
            courseIndex = allSystemCourses.indexOf(course);
            if(NOT_FOUND != courseIndex) {
                allSystemCourses.get(courseIndex).joinCourse(this);
                return true;
            }else return false;
        }else return false;
    }

    /**
     * This method oeverrided from object class and return student information as a string
     * @return
     */
    @Override
    public String toString() {
        return String.format(" STUDENT : "+super.toString() + " Student ID : " + getStudentID());
    }

    /**
     * This method takes a course and count item in this course then return string of items to see
     * @param course Course which it's item will return
     * @return course items
     */
    @Override
    public String showCourseItems(Course course) {
        if (null != course) {
            int indexOfCourse = takenCourses.indexOf(course);
            if (NOT_FOUND != indexOfCourse) {
                return takenCourses.get(indexOfCourse).showItems();
            }
        }
        return null;
    }

}
