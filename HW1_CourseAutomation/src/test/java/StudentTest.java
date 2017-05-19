import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Hasan MEN on 22.02.2016.
 */


public class StudentTest {

    private Student studentTest;
    private Course testCourse;

    @Before
    public void setUp() {
        studentTest = new Student("Mercan", "Karacabey", "mkaracabey", "123", "a@gmail.com", 131044009);
        testCourse = new Course("CSE222");
    }

    @After
    public void tearDown() {
        studentTest = null;
        testCourse = null;
    }

    /**
     * I can't test this function without make admin,corse,system. Must be test with other classes
     * @throws Exception
     */
    @Test
    public void testEnrolCourse() throws Exception {

        /*boolean actual;
        actual =  studentTest.enrolCourse(testCourse);;


        assertEquals(true,actual);

*/

    }
}
