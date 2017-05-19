package com.hasanmen;

import java.io.IOException;

/**
 * BU class assembler clasini test etmek amaclidir.
 * Created by hmenn on 3/20/16.
 */
public class Main {
	public static void main(String[] args) {

		String postFix;
		String asmExpression;
		String line;
		HMFileReader inputFileReader = new HMFileReader();
		HMFileWriter postFixFileWriter = new HMFileWriter();
		HMFileWriter asmFileWriter = new HMFileWriter();

		InfixToPostfix infix = new InfixToPostfix();
		Assembler assembler = new Assembler();

		try {
			// dosyalarin okunmasi ve store edilmesi
			inputFileReader.openFile("input.txt");
			postFixFileWriter.openFile("postfix.txt");
			// dosyayi sonuna kadar oku
			while (null != (line = inputFileReader.readLine())) {
				if (line.startsWith("print")) {
					assembler.addExpression(line);
					postFixFileWriter.writeFile(line + "\n");
				} else {
					postFix = infix.convert(line);
					assembler.addExpression(postFix);
					System.out.println(postFix);
					postFixFileWriter.writeFile(postFix + "\n");
				}
			}
			// tum dosyayi asm ye cevir
			asmExpression = assembler.createASM();
			// asmyi output dosyasina bas
			asmFileWriter.openFile("output.asm");
			asmFileWriter.writeFile(asmExpression);

			asmFileWriter.closeFile();
			inputFileReader.closeFile();
			postFixFileWriter.closeFile();
		} catch (InfixToPostfix.SyntaxErrorException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
		}
	}
}
