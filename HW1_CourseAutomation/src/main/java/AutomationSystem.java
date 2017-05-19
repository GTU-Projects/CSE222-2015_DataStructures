import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasan MEN on 20.02.2016.
 * This class created for automatin systems.
 * The system has users and courses. Courses can manage with admin or teachers.
 * First create of system, constructor will add one admin to the system.
 * NOT : GETTERS are public but nobuody can access real course with out login.
 */
public class AutomationSystem {


    // name of course automation system
    private String name;

    // courses in the automation system
    private List<Course> courses = new ArrayList<>();

    // users in automation system
    private List<User> users = new ArrayList<>();


    /**
     * CONSTRUCTOR  : CREATES SYSTEM AND ADDS ONE DEFAULT ADMIN
     *
     * @param name name of course automation system
     */
    public AutomationSystem(String name) {
        this.name = name;
        users.add(new Admin("Hasan", "MEN", "hmenn", "hasan5669", "hmen@gtu.edu.tr"));
    }


    /**
     * Creates a string which include contents of system end returns.
     */
    @Override
    public String toString() {

        StringBuilder strBldr = new StringBuilder();
        strBldr.append(name + " : Number of users -> " + users.size() + " - Number of Courses -> " + courses.size() +
                "\n\tAll courses in system :");

        for (int i = 0; i < courses.size(); ++i) {
            strBldr.append("\n\t" + courses.get(i));

        }
        return strBldr.toString();
    }


    /**
     * Compares information and if the user in the system returns a reference of user. Other wise returns null
     *
     * @param userName username of user
     * @param passWord password of user
     * @return reference of user or null
     */
    public User login(String userName, String passWord) {
        try {
            for (User usrItr : users) {
                if (userName.equals(usrItr.getUserName()) && passWord.equals(usrItr.getPassWord())) {
                    if (usrItr instanceof Admin) {
                        Admin logedAdmin = (Admin) usrItr;
                        logedAdmin.setSystemCourses(courses);
                        logedAdmin.setSystemUsers(users);
                        return logedAdmin;
                    } else if (usrItr instanceof Teacher) {
                        Teacher logedTeacher = (Teacher) usrItr;
                        return logedTeacher;
                    } else if (usrItr instanceof Student) {
                        //System.out.println("TEST");
                        Student logedStudent = (Student) usrItr;
                        logedStudent.setAllSystemCourses(courses);
                        return logedStudent;
                    }else throw new Exception("ILLEGAL LOGIN. PLEASE CHECK USERNAME AND PASSWORD. PROGRAM ABORTED!");
                }
            }
            throw new Exception("ILLEGAL LOGIN. PLEASE CHECK USERNAME AND PASSWORD. PROGRAM ABORTED!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
