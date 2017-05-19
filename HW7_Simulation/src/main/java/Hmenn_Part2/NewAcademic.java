package Hmenn_Part2;

/**
 * This class contains new academic card informations
 * All academic staff will hafe newAcademicBarcode which combined form student and acamdeic barcode numbers
 *
 * Created by hmenn on 4/30/16.
 */
public class NewAcademic implements IntPerson {

	private String name; // stuff name
	private int newAcademicBarcode; // new academic barcode number

	/**
	 * Constructor
	 * @param name name
	 * @param newAcademicBarcode new academic barcode number
	 */
	public NewAcademic(String name, int newAcademicBarcode) {
		this.name = name;
		this.newAcademicBarcode = newAcademicBarcode;
	}

	/**
	 * This method gets name of staff
	 * @return name of stagg
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * This method gets new academic barcode number
	 * @return new academic barcdde number
	 */
	public int getNewAcademicBarcode() {
		return newAcademicBarcode;
	}

	/**
	 * This method compares new academic person cards
	 * @param o other card
	 * @return if equal true, otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NewAcademic that = (NewAcademic) o;

		if (newAcademicBarcode != that.newAcademicBarcode) return false;
		return name != null ? name.equals(that.name) : that.name == null;

	}

	/**
	 * New card numbers will be hashing according to new prime number = 17
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 17 * result + newAcademicBarcode;
		return result;
	}

	@Override
	public String toString() {
		return "NewAcademic -> Name : "+name+" Barcode : "+newAcademicBarcode;
	}
}
