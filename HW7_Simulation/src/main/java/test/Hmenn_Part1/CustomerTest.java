package test.Hmenn_Part1;

import Hmenn_Part1.Customer;
import Hmenn_Part1.TimeHM;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Customer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 30, 2016</pre>
 */
public class CustomerTest {

	private Customer customer1 = new Customer(new TimeHM(5),new TimeHM(10),3);

	/**
	 * Method: getType()
	 */
	@Test
	public void testGetType() throws Exception {


		assertEquals(3,customer1.getType());

		try{

			Customer t = new Customer(null,null,-1);

		}catch (IllegalArgumentException e){
			System.out.println("Illegal arguments handled.");
		}
	}

	/**
	 * Method: getTotalTime()
	 */
	@Test
	public void testGetTotalTime() throws Exception {


		assertEquals(15,customer1.getTotalTime());
		System.out.println("getTotalTime test completed.");
	}



} 
