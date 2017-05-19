package com.hasanmen;

/**
 * Bu klass operandlari tutmak icin kullanilacak.
 * Created by hmenn on 3/20/16.
 */
public class Operand {
	// operandimiz
	private String data;

	/**
	 * Operand constructorumuz. String olarak operator atamasi yapar.
	 * @param data operand degeri
	 */
	public Operand(String data) {
		this.data = data;
	}

	/**
	 * Bu method operandimizin degerini return eder.
	 * @return operand degeri
	 */
	public String getOperandData() {
		return this.data;
	}

	/**
	 * Bu method operandimizi set etmemizi saglar
	 * @param newData yeni operand datamiz
	 */
	public void setData(String newData){
		this.data = newData;
	}


	/**
	 * Bu method dis ortamda kullanilacak.
	 * Kendisine gelen operandin sayi olup olmadigini kontrol eder.
	 * @param op sayi kontrolu yapilacak operand
	 * @return sayi ise true, diger durumlarda false return eder
	 */
	public static boolean isNumber(Operand op) {
		try {
			Integer.parseInt(op.getOperandData());
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Operand karsilastirmasi icin equal metodu.
	 * Delegation kullanilarak islemler kolaylastirildi.
	 * @param o Operand objemiz.
	 * @return esit ise true, degil ise false return eder
	 */
	@Override
	public boolean equals(Object o) {
		return data.equals(((Operand)o).getOperandData());
	}

	/**
	 * Operand clasi toString metodu
	 * @return operand degerinin string olarak karsiligi
	 */
	@Override
	public String toString() {
		return data;
	}
}
