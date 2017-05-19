package com.hasanmen;

/**
 * Bu klas operatorleri tutmak icin olusturuldu.
 * Her operatorun onceligi farklı olarak ele alindi.
 * 6 tane operator tutabiliriz. = * / + - print
 *
 * @version 1.0
 *          Created by hmenn on 3/20/16.
 */
public class Operator {

	private String data;

	// Constructor


	class OperatorException extends Exception {
		OperatorException(String message) {
			super(message);
		}
	}
	/**
	 * String olarak operator alir ve operator objesi olusturur.
	 *
	 * @param data yeni operator
	 * @throws OperatorException gecersiz operator ilklendirilmesi sirasinda firlatilir.
	 */
	public Operator(String data) throws OperatorException{

		if(isOperator(data))
			this.data = data;
		else throw new OperatorException("Invalid operator. Check operator parameter.");
	}

	/**
	 * Bu method operatoru string olarak return eder.
	 * @return icerdigi operatorun string olarak degeri
	 */
	public String getOperator() {
		return data;
	}

	/**
	 * Bu method operatorun program isleyisi icindeki onceligini retun eder.
	 * Bazı operatorlerin oncelikleri esit olabilir.
	 * Print operatorunun onceligi yoktur
	 * @return operatorun onceligi
	 */
	public int getPrecedence() {

		switch (data) {
			case "=":
				return 1;
			case "+":
				return 2;
			case "-":
				return 2;
			case "*":
				return 3;
			case "/":
				return 3;
			default:
				return -1;
		}
	}

	/**
	 * Bu static method baska classlardan erisim ve obje olusturmya ihtiyac kalınmamasi icin yapildi.
	 * Kendisine gelen stringin bir operator olusturup olusturamayacagini kontol eder.
	 * @param operator operator ozelligi kontrol edilecek string
	 * @return operator ise true, diğer durumlarda false return edecektir.
	 */
	public static boolean isOperator(String operator) {
		return operator.equals("=") || operator.equals("+") || operator.equals("-") ||
				operator.equals("*") || operator.equals("/") || operator.equals("print");
	}

	/**
	 * Bu method operatorun string olarak ciktisini almamizi saglar.
	 * @return
	 */
	@Override
	public String toString() {
		return data.toString();
	}
}
