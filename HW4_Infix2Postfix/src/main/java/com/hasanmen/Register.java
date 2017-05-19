package com.hasanmen;

/**
 * Bu method registerleri tutmak icin kullanilacak. Her registerin id si ve bir degeri vardir.
 * Eger register gecici olarak kullanilacaksa temp degeri tutulur.
 * Created by hmenn on 3/20/16.
 */
public class Register {

	private final int id; // id atandiktan sonra degisemez
	private Operand value;
	private boolean tempStatus = false; // eger temp ise gerekli durumlarda silinebilecek

	/**
	 * ID alarak register olusturan constuctor.
	 * Registerin operandi bos olarak atanir.
	 *
	 * @param id registerin yeni id si
	 */
	public Register(int id) {
		value = new Operand("");
		this.id = id;
	}

	/**
	 * Gecici bir register olusturmak icin register constructoru
	 * Register pek kayda alinip listelenmeyecegi icin id si -1 olarak atanir
	 *
	 * @param value -> registerin degeri, icerigi
	 */
	public Register(Operand value) {
		this.value = new Operand(value.getOperandData());
		id = -1;
	}

	/**
	 * Registerin gecicilik degerini kontrol eder. Temp olan registerler sonradan yok edilmek icin kontrol edilecek.
	 *
	 * @return registerin temp degeri
	 */
	public boolean getTempStatus() {
		return tempStatus;
	}

	/**
	 * Registerin gecici olmasini saglayacak veriyi set eder.
	 *
	 * @param tempStatus yeni temp degeri
	 */
	public void setTempStatus(boolean tempStatus) {
		this.tempStatus = tempStatus;
	}

	/**
	 * Registerin id degerini return eder.
	 *
	 * @return register id si
	 */
	public int getId() {
		return id;
	}

	/**
	 * Registerin icerdigi degeri set eder.
	 *
	 * @param value yeni register icerigi
	 */
	public void setValue(Operand value) {
		String data = value.getOperandData();
		this.value.setData(data);
	}

	/**
	 * Registerin icerigini return eder.
	 *
	 * @return registerin icerigi
	 */
	public Operand getValue() {
		return this.value;
	}

	/**
	 * Registerin id sini string olarak return eder.
	 *
	 * @return register in id sini return eder.
	 */
	@Override
	public String toString() {
		return "$t" + id;
	}

	/**
	 * Registerleri karsilastirmak icin equal metodu
	 *
	 * @param o karsilastirilacak register
	 * @return esitlik durumunda true, diger durumlarda false return eder
	 */
	@Override
	public boolean equals(Object o) {
		return value.equals(((Register) o).getValue());
	}

	/*############################################################*/
	/* BURADAKI METODLAR ASSEMBLY CIKTISI ALMAK ICIN KULLANILACAK */
	/*############################################################*/

	/**
	 * assembly olarak li metoduna cevirim yapar. Li ederken sadece operandin degerini bilmek yeterli
	 *
	 * @param newValue registere atanacak degeri ifade eder
	 * @return registere atanmasinin asm karsiligi
	 */
	public String liRegister(int newValue) {
		return "li\t" + this + "," + newValue + "\n";
	}

	/**
	 * bu method cikarma islemini assembly olarak ifade etmek icin kullanilacak
	 * this = rg1 - rg2
	 * sub $t3,$t1,$t2
	 *
	 * @param rg1 cikarma yapilacak register
	 * @param rg2 cikarilan register
	 * @return islemin string olarak karsiligi
	 */
	public String subRegister(Register rg1, Register rg2) {
		return "sub\t" + this + "," + rg1 + "," + rg2 + "\n";
	}

	/**
	 * bu method toplama islemini assembly olarak ifade etmek icin kullanilacak
	 * this = rg1 + rg2
	 * add $t3,$t1,$t2
	 *
	 * @param rg1 toplama nin ilk operandi
	 * @param rg2 toplamanin ikinci operandi
	 * @return islemin string olarak karsiligi
	 */
	public String addRegister(Register rg1, Register rg2) {
		return "add\t" + this + "," + rg1 + "," + rg2 + "\n";
	}

	/**
	 * Bu method carpma islemini ifade etmek icin kullanilir.
	 * this * rg2
	 * mult $t1,$t2
	 *
	 * @param rg2 carpmanin ikinci operandi
	 * @return islemin asm karsiligi
	 */
	public String multRegister(Register rg2) {
		return "mult\t" + this + "," + rg2 + "\n";
	}

	/**
	 * Store edilmis ifadeleri gostermek icin kullanilacak
	 * Carma,bolme islemlerinin sonucu mflo icine kaydedilir
	 * sonuc = a
	 * mflo $t2, $t1
	 *
	 * @return store ifadesinin string karsiligi
	 */
	public String mfloRegister() {
		return "mflo\t" + this + "\n";
	}

	/**
	 * Bu method bolme islemini ifade etmek icin kullanilir.
	 * this / rg2
	 * div $t1,$t2
	 *
	 * @param rg2 bolmenin ikinci operandi
	 * @return islemin asm karsiligi
	 */
	public String divRegister(Register rg2) {
		return "div\t" + this + "," + rg2 + "\n";
	}

	/**
	 * Bu method li metoduna benzer ama sabit sayilari atamak icin degilde farklı registerlerden atama yapmak
	 * icin kullanilaca
	 * <p>
	 * b = a
	 * move $t2,$t1
	 *
	 * @param rg2 kopyalanacak register
	 * @return atama isleminin asm karsiligi
	 */
	public String moveRegister(Register rg2) {
		return "move\t" + this + "," + rg2 + "\n";
	}
}

/*#######################################*/
/* PACKAGE VISIBILITY OLMASI YETERLI     */
/*#######################################*/

/**
 * Bu klas asm de kullanilacak v0 registerini ifade etmek icin kullanilacak.
 * Sadece v0 registerinin ciktisini farkli almak icin kullanilacak.
 */
class VRegister extends Register {
	public VRegister(int id, Operand op) {
		super(id);
		setValue(op);
	}

	public String toString() {
		return "$v" + getId();
	}

}

/**
 * BU klass asm de a0 registerini ifade etmek icin kullanilacak.
 * a0 registeri print operatoru icin kulanilacak.
 * Store edilmesine gerek olmayan objelerimiz
 */
class ARegister extends Register {


	public ARegister(int id, Operand op) {
		super(id);
		setValue(op);
	}

	public String toString() {
		return "$a" + getId();
	}


}
