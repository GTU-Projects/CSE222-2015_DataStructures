package com.hasanmen;

import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;


/**
 * Created by Hasan MEN on 6.03.2016.
 *
 * @version 1.03
 *          <p>
 *          This is a test class.
 *          Tests SpecList class methods.
 */
public class SpecListTest {

    private SpecList<Integer> firstSpecList;
    private SpecList<Integer> secSpecList;

    @org.junit.Before
    public void setUp() throws Exception {
        firstSpecList = new SpecList<>();
        secSpecList = new SpecList<>();

    }

    @org.junit.After
    public void tearDown() throws Exception {
        firstSpecList = null;
        secSpecList = null;

    }

    /**
     * This methods test addAllAtHead method.
     * Firstly, create a temp list with some numbers.
     * Calls addAllAtHead methods and adds all items in temp list to actual list
     * Then compares with expected list
     *
     * @throws Exception may throw jvm exceptions
     */
    @org.junit.Test
    public void testAddAllAtHead() throws Exception {

        System.out.println("\n## addAllAtHead TEST ##");
        try {

            firstSpecList.add(9);
            firstSpecList.add(3);

            System.out.println("Before adding firstSpecList :" + firstSpecList);
            // If list empty will return false
            boolean actual = firstSpecList.addAllAtHead(secSpecList);
            assertEquals(false, actual);

            secSpecList.add(2);
            secSpecList.add(4);

            System.out.println("secSpecList to add in firstSpecList :" + secSpecList);

            // Add filled list at the head
            actual = firstSpecList.addAllAtHead(secSpecList);
            assertEquals(true, actual);

            System.out.println("After adding firstSpecList :" + firstSpecList);

            // control exceptions
            firstSpecList.addAllAtHead(null);

        } catch (NullPointerException e) {
            System.out.println("Exception test : " + e.getMessage());
        }

    }

    /**
     * This method tests getIntersectList method.
     * Firstly, created 4list for testin. Adds some objects int first and second list.
     * Then calls getIntersectMethods to obtaion intersect list.
     * I added possible items to myIntersect list and I will compare intersect list with my list.
     *
     * @throws Exception Possible exceptions like null pointer
     */
    @org.junit.Test
    public void testGetIntersectList() throws Exception {

        System.out.println("\n## getIntersectList TEST ##");
        try {
            List<Integer> actualIntersects;
            SpecList<Integer> expectedIntersects = new SpecList<>();

            // Add some test numbers
            firstSpecList.add(1);
            firstSpecList.add(2);
            firstSpecList.add(4);
            firstSpecList.add(3);

            System.out.println("Elements of firstSpecList" + firstSpecList);

            // Add some test numbers
            secSpecList.add(4);
            secSpecList.add(6);
            secSpecList.add(7);
            secSpecList.add(2);
            secSpecList.add(0);

            System.out.println("Elements of secSpecList" + secSpecList);

            // Possible common elements
            expectedIntersects.add(2);
            expectedIntersects.add(4);

            System.out.println("Elements of expectedIntersects" + expectedIntersects);

            // get intersectlist
            actualIntersects = firstSpecList.getIntersectList(secSpecList);

            System.out.println("Elements of actualIntersects" + actualIntersects);

            // compare lists - test successfull
            assertEquals(expectedIntersects, actualIntersects);

            // TEST FOR SENDING NULL LIST
            actualIntersects = secSpecList.getIntersectList(null);
            assertEquals(null, actualIntersects);

        } catch (NullPointerException e) {
            System.out.println("Exception test : " + e.getMessage());
        }
    }


    /**
     * This method tests sortList method.
     *
     * @throws Exception empty
     */
    @org.junit.Test
    public void testSortList() throws Exception {

        List<Integer> sortedList = new SpecList<>();

        System.out.println("\n## sortList TEST ##");

        // complex list
        firstSpecList.add(11);
        firstSpecList.add(23);
        firstSpecList.add(0);
        firstSpecList.add(-5);
        firstSpecList.add(18);
        firstSpecList.add(2);

        // expected list order
        secSpecList.add(-5);
        secSpecList.add(0);
        secSpecList.add(2);
        secSpecList.add(11);
        secSpecList.add(18);
        secSpecList.add(23);

        System.out.println("firstSpecList before sorting :" + firstSpecList);

        // sort list
        sortedList = firstSpecList.sortList(true);

        System.out.println("firstSpecList after sorting :" + sortedList);

        // test one okey
        assertEquals(secSpecList, sortedList);

    }
}