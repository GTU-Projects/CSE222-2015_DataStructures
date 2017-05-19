import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasan MEN on 21.02.2016.
 * This class store teacher information.
 * Teacher class extended from User list.
 * A teacher can have courses, can accept cuorse enrol requests.
 */
public class Teacher extends User {

    /**
     * Teacher given courses lists.Will be load by administrator.
     */
    private List<Course> givenCourses = new ArrayList<>();
    public static final int NOT_FOUND = -1;

    /**
     * Teacher CONSTRUCTOR
     * Creates new teacher objects and initialize it.
     *
     * @param name     Name of Teacher
     * @param surName  Surname of Teacher
     * @param userName Username of Teacher
     * @param passWord Password of teacher
     * @param eMail    Email address of teacher
     */
    public Teacher(String name, String surName, String userName, String passWord, String eMail) {
        super(name, surName, userName, passWord, eMail); // call super class constructor
    }

    /**
     * This method access teacher given classes. For tests, it is public.IT'S NOT PROBLEM BECAUSE YOU MUST LOGIN
     * TO SYSTEM TO USE
     *
     * @return list of courses given by teacher
     */
    public List<Course> getGivenCourses() {
        return givenCourses;
    }


    /**
     * This method founds course in given courses then, counts the enrolments for actual course.Store them in a string.
     *
     * @param course Actual course in geven courses
     * @return String of course enrolments
     */
    public String showEnrolments(Course course) {
        StringBuilder strBldr = new StringBuilder("");

        int index = givenCourses.indexOf(course);
        if (NOT_FOUND != index) {
            for (int i = 0; i < givenCourses.get(index).getCourseRequests().size(); ++i) {
                strBldr.append(givenCourses.get(index).getCourseRequests().get(i).toString() + "\n");
            }
            return strBldr.toString();
        } else return new String("COURSE NOT FOUNDED IN GIVEN COURSES");
    }

    /**
     * This method just for teacher. There is no override. Teacher can accept course enrol requests.
     * If parameters are true will try add student in actual course user lists.
     *
     * @param course  Course , which student will add
     * @param student Student reference to add in course
     * @return If student added in course return true, otherwise false
     */
    public boolean acceptEnrolment(Course course, User student) {

        try {
            if (course instanceof Course && student instanceof Student) {
                int indexOfCourse = getGivenCourses().indexOf(course);
                if (NOT_FOUND == indexOfCourse)
                    throw new Exception("COULDN'T FOUND COURSE. ENROLMENT NOT ACCEPTED!!!");

                Course realCourse = getGivenCourses().get(indexOfCourse);

                int indexOfSudent = getGivenCourses().get(indexOfCourse).courseRequests.indexOf(student);
                if (NOT_FOUND == indexOfSudent)
                    throw new Exception("COULDN'T FOUND USER. ENROLMENT NOT ACCEPTED!!!");
                User realStudent = getGivenCourses().get(indexOfCourse).courseRequests.get(indexOfSudent);

                // find user in general list and add him/her to course. TO SAVE REFERANCES
                addStudent(realCourse, realStudent);

                // remove request
                getGivenCourses().get(indexOfCourse).courseRequests.remove(indexOfSudent);

                return true;
            }
            throw new Exception("COURSE OR STUDENT NULL. ENROLMENT COULDN'T ACCEPTED.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This function will be use just for add student direct in course. It's private no need to make public
     *
     * @param course  Course which student will add
     * @param student Which student will add in course
     * @return Sttatus of student add or not
     */
    private boolean addStudent(Course course, User student) {
        course.getCourseStudents().add(student);
        return true;
    }

    /**
     * This method takes a course and if it valid, access enrolments in course, then  counts the students
     *
     * @param course Course
     * @return String of enrolments
     */
    public String showCourseStudents(Course course) {
        if (null != course) {
            int indexOfCourse = getGivenCourses().indexOf(course);
            if (NOT_FOUND != indexOfCourse) {
                StringBuilder strBldr = new StringBuilder("");
                for (int i = 0; i < getGivenCourses().get(indexOfCourse).getCourseStudents().size(); ++i) {
                    strBldr.append(getGivenCourses().get(indexOfCourse).getCourseStudents().get(i) + "\n");
                }
                return strBldr.toString();
            }
        }
        return new String("COURSE NOT FOUNDED. SHOW ENROLMENTS NOT WORKED PROPERLY.");

    }

    @Override
    /**
     * This method returns teacher information like a string
     */
    public String toString() {
        return String.format("Teacher : Name -> " + getName() + " " + getSurName() + " - E-mail -> " + geteMail());
    }

    /**
     * This method takes course and course item. If course available and item not null will add item in course
     *
     * @param course Which course, item will be add
     * @param item   new Course item to add
     * @return Status of adding
     */
    public boolean addCourseItem(Course course, CourseItem item) {
        try {
            if (null != item && null != course) {
                int indexOfCourse = givenCourses.indexOf(course);
                if (NOT_FOUND != indexOfCourse) {
                    givenCourses.get(indexOfCourse).addCourseItem(item);
                    return true;
                } else throw new Exception("COURSE NOT FOUNDED. PLEASE USE TRUE COURSE. METHOD NOT WORKED TRUE.");
            }
            throw new Exception(" COURSE OR ITEM NULL. ADD ITEM TO COURSE NOT WORKED PROPERLY.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This function takes one course and count items to add in a string. Then return all course items like a string.
     *
     * @param course A course referance to find in real system courses.
     * @return String of course items
     */
    @Override
    public String showCourseItems(Course course) {
        try {
            if (null != course) {
                int indexOfCourse = givenCourses.indexOf(course);
                if (NOT_FOUND != indexOfCourse) {
                    return givenCourses.get(indexOfCourse).showItems();
                }
            }
            throw new Exception("INVALID COURSE REFERANCE. SHOW COURSE ABORTED. PLEASE CHECK PARAMETER.");
        } catch (Exception e) {
            return null;
        }
    }

}
