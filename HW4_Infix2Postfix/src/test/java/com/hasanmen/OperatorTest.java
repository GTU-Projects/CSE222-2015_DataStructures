package com.hasanmen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * BU test operator klasini test edecektir.
 * Operator klasi cok sade oldugu icin testler cok kisadir.
 * Created by hmenn on 3/22/16.
 */
public class OperatorTest {

	Operator addOp;
	Operator assignOp;

	/**
	 * Test edilmesi icin objelerin initialize islemleri yapilir.
	 * @throws Exception Bu klass icin olagan exception yoktur.
	 */
	@Before
	public void setUp() throws Exception {
		addOp = new Operator("+");
		assignOp = new Operator("=");
	}

	/**
	 * Olusturulan objelerin isleri bittikten sonra destructor gibi davranacak method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		addOp = null;
		assignOp = null;
	}

	/**
	 * Bu method operator cagirarak icerigini kontrol eder.
	 * @throws Exception olasi hata yoktur.
	 */
	@Test
	public void getOperatorTest() throws Exception {

		// + operatoru icin
		assertEquals("+",addOp.getOperator());

		// = operatoru icin
		assertEquals("=",assignOp.getOperator());

	}

	/**
	 * BU test methodu operatorun onceliginin dogrulugunu kontrol eder.
	 * @throws Exception olasi exceptionlar yoktur.
	 */
	@Test
	public void getPrecedenceTest() throws Exception {

		// + nin onceligi 2 olarak belirlenmisti
		assertEquals(2,addOp.getPrecedence());

		// = nin onceligi 1 olarak belirlenmisti
		assertEquals(1,assignOp.getPrecedence());

	}

	/**
	 * Bu test verilen stringin operator olma sartini kontrol eder.
	 * @throws Exception olasi exceptionlar yoktur
	 */
	@Test
	public void isOperatorTest() throws Exception {

		// operator olamaz
		assertEquals(false,Operator.isOperator("?"));

		// olagan operator
		assertEquals(true,Operator.isOperator("/"));

	}

	/**
	 * Bu method operatorun ciktisini kontrol eder.
	 * @throws Exception olasi exception yoktur
	 */

	@Test
	public void toStringTest() throws Exception{
		assertEquals("+",addOp.toString());
	}

	@Test
	public void operatorExceptionTest() throws Exception{


	}


}