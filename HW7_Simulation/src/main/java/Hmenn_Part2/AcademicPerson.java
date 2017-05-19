package Hmenn_Part2;

/**
 * This class contains academic persons lite teachers
 * Created by hmenn on 4/30/16.
 */
public class AcademicPerson implements IntPerson {

	private String name;
	private int academicBarcode;

	/**
	 * Constructor
	 * @param name name
	 * @param academicBarcode academic barcode number
	 */
	public AcademicPerson(String name, int academicBarcode) {
		this.name = name;
		this.academicBarcode = academicBarcode;
	}

	/**
	 * Standart java equal method worked for me.
	 * I didn't change this.
	 *
	 * This method compares two person object
	 *
	 * @param o other person
	 * @return if equal true, otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AcademicPerson that = (AcademicPerson) o;

		if (academicBarcode != that.academicBarcode) return false;
		return name != null ? name.equals(that.name) : that.name == null;
	}

	public int getAcademicBarcode() {
		return academicBarcode;
	}


	/**
	 * This method hashes class members
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 17 * result + academicBarcode;
		return result;
	}

	/**
	 * Returns name
	 * @return name
	 */
	public String getName(){
		return this.name;
	}


	@Override
	public String toString() {
		return "AcademicPerson -> Name : "+name+" A.Barcode : "+academicBarcode;
	}
}
