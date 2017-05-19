package com.hasanmen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Bu klass operand klasinin test metodlarini icerir.
 * Created by hmenn on 3/22/16.
 */
public class OperandTest {

	Operand op1;
	Operand op2;
	Operand op3;
	@Before
	public void setUp() throws Exception {
		op1 = new Operand("denemee");
		op2 = new Operand("36");
		op3 = new Operand("2a6");

	}

	@After
	public void tearDown() throws Exception {
		op1=null;
		op2=null;
		op3=null;
	}

	@Test
	public void getOperandDataTest() throws Exception {

		// getters okey
		assertEquals("denemee",op1.getOperandData());

		assertEquals("36",op2.getOperandData());

	}

	@Test
	public void setDataTest() throws Exception {

		op1.setData("hahahaha");
		assertEquals("hahahaha",op1.getOperandData());

	}

	@Test
	public void isNumberTest() throws Exception {

		// op2 nin degeri 36 ve bir sayi
		assertEquals(true,Operand.isNumber(op2));

		// 2a6 bir sayi degildir.
		assertEquals(false,Operand.isNumber(op3));

	}
}