package Hmenn_Part2;


/**
 * This class contains research asistant informations
 * A research asistan is a person and academic person
 * Every asistant has student barcode and academic barcode
 * Created by hmenn on 4/30/16.
 */
public class ResearchAsistant extends  AcademicPerson implements IntPerson {

	private int studentBarcode;

	/**
	 * Constructor
	 * @param name asistant name
	 * @param academicBarcode academic barcode
	 * @param studentBarcode student barcode
	 */
	public ResearchAsistant(String name, int academicBarcode, int studentBarcode) {
		super(name, academicBarcode);
		this.studentBarcode = studentBarcode;
	}

	/**
	 * This method compares two asistant
	 * @param o other person
	 * @return if equal true,otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true; // control references
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false; // control super equal

		ResearchAsistant that = (ResearchAsistant) o;

		// lastly, control barcode numbers
		return studentBarcode == that.studentBarcode;

	}

	/**
	 * This method hashes asistant information and creates new barbode(hashCode)
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		// 17 is enough for mee
		result = 17 * result + studentBarcode;
		return result;
	}


	@Override
	public String toString() {
		return "ResearchAsistant -> Name : " +getName()+" A.Barcode : "+getAcademicBarcode()+" S.Barcode : "+studentBarcode;
	}
}
