
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Hasan MEN on 21.02.2016.
 */
public class AdminTest {

    Admin adminT;

    @Before
    public void setUp() throws Exception {
        adminT= new Admin("ADMIN","TEST","admin","admin","admin@email.com");
    }

    @After
    public void tearDown() throws Exception {
        adminT=null;
    }

    /**
     * THIS FUNCTIONS TEST ADDING NEW COURSE TO THE SYSTEM
     * @throws Exception
     */
    @Test
    public void testAddCourse() throws Exception {

        boolean expected=true;
        boolean actual = false;

        actual = adminT.addCourse(new Course("CSE222"));
        assertEquals(expected,actual);

        actual = adminT.addCourse(new Course("CSE222"));
        expected=false;
        assertEquals(expected,actual);
    }

    @Test
    public void testAddTeacher() throws Exception {

        boolean expected=true;
        boolean actual = false;

        // adding first time
        Course courseT = new Course("CSEXXX");
        adminT.addCourse(courseT);
        actual = adminT.addTeacher(courseT,new Teacher("NECMEDDIN","CARKACI","NC","NC","nc@gmail.com"));

        assertEquals(expected,actual);

        // Adding same teacher to the same course
        expected=false;
        actual = adminT.addTeacher(courseT,new Teacher("NECMEDDIN","CARKACI","NC","NC","nc@gmail.com"));
        assertEquals(expected,actual);


        // Adding teacher to general user list
        expected = true;
        actual = adminT.addTeacher(new Teacher("ARZU","KAKISIM","arzu","arzu","a@gmail.com"));
        assertEquals(expected,actual);

        // Adding same teacher to the general user list
        expected = false;
        actual = adminT.addTeacher(new Teacher("ARZU","KAKISIM","arzu","arzu","a@gmail.com"));
        assertEquals(expected,actual);

    }



}