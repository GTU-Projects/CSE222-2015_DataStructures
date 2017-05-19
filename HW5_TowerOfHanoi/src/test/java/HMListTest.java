import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * HMList Tester.
 *
 * @author <hmenn>
 * @version 1.0
 * @since <pre>Mar 31, 2016</pre>
 */
public class HMListTest {

	private HMList<String> hml;

	@Before
	public void before() throws Exception {
		hml = new HMList<>();


	}

	@After
	public void after() throws Exception {
		hml = null;
	}

	/**
	 * Method: addElements(int listNum, E ... items)
	 */
	@Test
	public void testAddElements() throws Exception {

		boolean actual;

		System.out.println("#### TEST ADD ELEMENTS ####");

		// 1.liste icin null yolla
		actual = hml.addElements(1, null);
		assertEquals(false, actual);

		// 2.liste icin null yolla
		actual = hml.addElements(2, null);
		assertEquals(false, actual);

		try {
			// 3.listeye ekleme esnasinda exception atsin
			actual = hml.addElements(3, null);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		//ekleme basarili
		actual = hml.addElements(1, "ates", "ATES", "ATES", "hasan", "men", "men", "mercan");
		assertEquals(true, actual);

		// ekleme basarili
		actual = hml.addElements(2, "ates", "berat", "busra", "hasan", "mercan", "mercan");
		assertEquals(true, actual);

	}


	/**
	 * Method: intersectionOfList()
	 */
	@Test
	public void testIntersectionOfList() throws Exception {

		System.out.println("\n#### TEST INTERSECTION OF LISTS ####");

		//NULL KONTROL
		assertEquals(null, hml.intersectionOfList());


		hml.addElements(1, "ates", "ATES", "ATES", "hasan", "men", "men", "mercan");
		hml.addElements(2, "ates", "berat", "busra", "hasan", "mercan", "mercan");

		hml.printList(1);
		hml.printList(2);
		List<String> intr = hml.intersectionOfList();
		List<String> expected = new ArrayList<>();
		expected.add("mercan");
		expected.add("hasan");
		expected.add("ates");

		// kesisim listesini dogru veriyor.
		System.out.println("Inter1 :" + intr);
		System.out.println("Expected1 : " + expected);
		assertEquals(expected, intr);

		// bir eleman daha ekleyip false durumuu kontrol edelim

		expected.add("Seyma");
		assertNotEquals(expected, intr);

	}


	/**
	 * Method: unionOfLists()
	 */
	@Test
	public void testUnionOfLists() throws Exception {


		System.out.println("\n#### TEST UNION OF LISTS ####");

		//NULL KONTROL
		assertEquals(null, hml.unionOfLists());


		hml.addElements(1, "ates", "ATES", "ATES", "hasan", "men", "men", "mercan");
		hml.addElements(2, "ates", "berat", "busra", "hasan", "mercan", "mercan");

		hml.printList(1);
		hml.printList(2);
		List<String> uni = hml.unionOfLists();
		List<String> expected = new ArrayList<>();
		expected.add("busra");
		expected.add("berat");

		// kesisim listesini dogru veriyor.
		System.out.println("Inter1 :" + uni);
		System.out.println("Expected1 : " + expected);
		assertEquals(expected, uni);

		// bir eleman daha ekleyip false durumuu kontrol edelim

		expected.add("Seyma");
		assertNotEquals(expected, uni);

	}


	/**
	 * Method: isSubset()
	 */
	@Test
	public void testIsSubset() throws Exception {

		System.out.println("\n#### TEST IS SUBSET ####");
		// Bos liste kontrolu
		assertEquals(false,hml.isSubset());


		hml.addElements(2,"a","b","c");
		hml.addElements(1,"a","b");

		hml.printList(1);
		hml.printList(2);
		assertEquals(false,hml.isSubset());
		System.out.println("FALSE : List2 bigger than List1 ");



		hml.addElements(2,"d");
		hml.addElements(1,"c","d","g");
		hml.printList(1);
		hml.printList(2);
		assertEquals(true,hml.isSubset());
		System.out.println("TRUE : List1 bigger than List2  and list2 subset of list1 ");
	}

}
