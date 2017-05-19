package com.hasanmen;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

/**
 * Created by hmenn on 3/23/16.
 */
public class AssemblerTest {

	Assembler assembler = new Assembler();

	@Test
	public void addExpressionTest() throws Exception {

		assembler.addExpression("a = 5");
		assembler.addExpression("b = 2");
	}

	@Test
	public void createASMTest() throws Exception {



	}

	@Test
	public void printTest() throws Exception {

		// ornek ciktisi boyle olmali
		StringBuilder str = new StringBuilder();
		str.append("move\t$a0,$t0\n");
		str.append("li\t$v0,1\n");
		str.append("syscall");


		// size ve kelimesi kelimesine ornekteki gibi olmali
		assembler.print(new Operand("a"));

		System.out.println(str.length());
		System.out.println(str.toString());
		System.out.println(assembler.getASMExpression().length());
		System.out.println(assembler.getASMExpression());

		assertEquals(str.toString(),assembler.getASMExpression());

	}

	@Test
	public void evaluateTest() throws Exception {

	}

	@Test
	public void freeRegisterTest() throws Exception {

		try {

			assembler.createRegister(new Operand("reg0"));

			// eger eleman icinde yoksa exception firlatir
			assembler.freeRegister(new Register(new Operand("reg0")));

		}catch(EmptyStackException e){
			System.out.println(e.getMessage());
			System.out.println("TEST FAILED");
		}

	}

	@Test
	public void getRegisterTest() throws Exception {

		// bos assemblerden register cekince null dondurmeli
		assertEquals(null,assembler.getRegister(new Operand("")));

		// icinie bir register atalim sonra tekrar register istersek id si 0 olmali
		assembler.createRegister(new Operand(""));
		assertEquals(new Register(0),assembler.getRegister(new Operand("")));

	}

	@Test
	public void createRegisterTest() throws Exception {

		// operandi a olan bir register olusturmak istersem
		// listede olmadigi icin olusturup bana return edecek

		assertEquals(0,assembler.createRegister(new Operand("a")).getId());


		// bir tane daha istersek ustune ekleyip 1 registeri yollamali
		assertEquals(1,assembler.createRegister(new Operand("c")).getId());



	}
}