import java.util.ArrayList;
import java.util.List;

/**
 * This class will use for first initialize of Course management system.
 */
public class Admin extends User {
    public static final int NOT_FOUND = -1;

    private List<Course> systemCourses = null; // erisileblinen kurslar
    private List<User> systemUsers = null;

    /**
     * ADMIN CONSTRUCTOR
     *
     * @param name name of admin
     * @param surName surname of admin
     * @param userName username for admin
     * @param passWord password for admin
     * @param eMail email address for admin
     */
    public Admin(String name, String surName, String userName, String passWord, String eMail) {
        super(name, surName, userName, passWord, eMail);
        systemCourses = new ArrayList<>();
        systemUsers = new ArrayList<>();
    }

    /**
     * THIS METHOD DANGEROUS BUT JUST LOGIN_SYSTEM CAN FILL ACTUAL USERS TO TRUE CONTROL
     * Getter for all system users
     * @return SYSTEM USERS LIST
     */
    public List<User> getSystemUsers() {
        return systemUsers;
    }

    /**
     * THIS METHOD DANGEROUS BUT JUST LOGIN_SYSTEM CAN FILL ACTUAL COURSES TO TRUE CONTROL
     * Getter for all system courses
     * @return SYSTEM courses LIST
     */
    public List<Course> getSystemCourses() {
        return systemCourses;
    }

    /**
     * Setter for system course
     * @param systemCourses new system courses arraylist
     */
    public void setSystemCourses(List<Course> systemCourses) {
        this.systemCourses = systemCourses;
    }

    /**
     * Setter for system users
     * @param systemUsers new users arraylist
     */
    public void setSystemUsers(List<User> systemUsers) {
        this.systemUsers = systemUsers;
    }

    /**
     * This funciton adds course to the system. If there are courses, has the same name
     * won't add course to the system.
     *
     * @param course course which trying to add system
     * @return status of adding courses to system
     */
    public boolean addCourse(Course course) {
        try {
            if (null != course) {
                if (!getSystemCourses().contains(course)) {
                    getSystemCourses().add(new Course(course));
                    return true;
                }
            }
            throw new Exception("ADD COURSE FAILED. THERE ARE SAME "+course+" IN THE SYSTEM.PLEASE CHECK COURSES.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * THIS FUNCTION ASSIGN TEACHER TO COURSE
     * If admin couldn't not see teacher in system adds new teacher in system and adds in the courses
     * Firstly adds in system then copy references of teacher to course so they will be synchronized.
     *
     * @param course where teacher will add
     * @param user   which teacher will add in course and in system
     * @return if add teacher is successfull return true otherwise return false
     */
    public boolean addTeacher(Course course, User user) {
        if (user instanceof Teacher) {
            // if there is course in the system

            int indexOfCourse = getSystemCourses().indexOf(course);
            if (null != course && NOT_FOUND != indexOfCourse) {
                // If not in the system add system and then add course
                Course realCourse = getSystemCourses().get(indexOfCourse);
                int indexOfTeacher = realCourse.getTeacherIndex(user); // look if there same teacher
                if (NOT_FOUND == indexOfTeacher) {
                    Teacher teacher = (Teacher) user;
                    teacher.getGivenCourses().add(realCourse);
                    addTeacher(teacher); // add teacher in the local system
                    getSystemCourses().get(indexOfCourse).getCourseTeachers().add(teacher); // add teacher in the course
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * THIS FUNCTION ADDS TEACHER TO THE SYSTEM
     *
     * @param user copy of teacher
     * @return return adding status
     */
    public boolean addTeacher(User user) {
        if (user instanceof Teacher) {
            if (!getSystemUsers().contains(user)) {
                getSystemUsers().add(user);
                return true;
            }
        }
        return false;
    }


    /**
     * This method takes a stutend and creates independent copy of usee then adds in general system users
     * @param user a student referance to add system
     * @return status of adding
     */
    public boolean addStudent(User user) {
        try {
            if (user instanceof Student) {
                if (!getSystemUsers().contains(user)) {
                    getSystemUsers().add(new Student((Student) user));
                    return true;
                }else throw new Exception("ADD STUDENT FAILED. STUDENT ALREADY IN COURSE!!!");
            }else throw new Exception("ADD STUDENT FAILED. PARAMETER NOT A STUDENT!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("## ADMIN ## Name : " + getName() + " Surname : " + getSurName() + " eMail : " + geteMail());
    }

    @Override
    public String showCourseItems(Course course) {
        if (null != course) {
            int indexOfCourse = systemUsers.indexOf(course);
            if (NOT_FOUND != indexOfCourse) {
                return systemCourses.get(indexOfCourse).showItems();
            }
        }
        return null;
    }
}
