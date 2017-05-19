package Hmenn_Part2;

import Hmenn_Part1.ArrayListHM;

import java.io.*;
import java.util.StringTokenizer;

/**
 * This class contain a service, this service will read old asistant and academic informations then will combine
 * this accounts/cards. Will create new barcode number for persons.
 * Created by hmenn on 4/30/16.
 */
public class Service {
	private ArrayListHM<IntPerson> persons; // persons
	private ArrayListHM<IntPerson> newPersons; // new person informations
	private String fileName; // input file name
	private HashTableOpen<Integer,IntPerson> table; // hashtable for new barcode numbers

	private int totalPersonNum; // total person number in system

	// readers
	private InputStream ips;
	private InputStreamReader ipsr;
	private BufferedReader br;


	/**
	 * Constructor
	 * @param fileName input file name
	 */
	public Service(String fileName){
		this.fileName=fileName;
		persons = new ArrayListHM<>();
		table = new HashTableOpen<>();
		newPersons = new ArrayListHM<>();
	}

	/**
	 * This method returns total person number in system
	 * @return total stuff number in system
	 */
	public int getTotalPersonNum() {
		return totalPersonNum;
	}

	/**
	 * Reads persons from file and returns number of persons
	 * @return number of persons
	 */
	public int readPersons() throws IOException {

		ips = new FileInputStream(fileName);
		ipsr = new InputStreamReader(ips);
		br = new BufferedReader(ipsr);

		String line;

		br.readLine();

		while(null != (line = br.readLine())){


			StringTokenizer tokenizer = new StringTokenizer(line," \t");

			while(tokenizer.hasMoreTokens()){
				//System.out.println(line);
				String name;
				String academicBarcode;
				String studentBarcode;

				name = tokenizer.nextToken();
				studentBarcode = tokenizer.nextToken();
				academicBarcode = tokenizer.nextToken();

				if(studentBarcode.equals("-"))
					persons.add(new AcademicPerson(name,Integer.parseInt(academicBarcode)));
				else persons.add(new ResearchAsistant(name,Integer.parseInt(academicBarcode),Integer.parseInt(studentBarcode)));
				//System.out.println(persons);
			}

		}
		br.close();
		return totalPersonNum = persons.size();
	}

	/**
	 * This methods combines old stuff informations and creates new stuff cards. Then add this card/barcodes in
	 * a hashtable. Key : barcode num  Value : stuff class
	 */
	public void createTable(){

		for (int i=0;i<persons.size();++i){
			NewAcademic newAcademic = new NewAcademic(persons.get(i).getName(),persons.get(i).hashCode());
			newPersons.add(newAcademic);
			table.put(persons.get(i).hashCode(),newAcademic); // add table
		}

		System.out.println("Added "+table.size()+" new person in hash table");
	}

}
