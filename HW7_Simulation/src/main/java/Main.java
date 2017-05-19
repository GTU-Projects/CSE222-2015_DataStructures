/**
 * Created by hmenn on 4/26/16.
 */


import Hmenn_Part1.ArrayListHM;
import Hmenn_Part1.Customer;
import Hmenn_Part1.SimulationHM;
import Hmenn_Part1.TimeHM;
import Hmenn_Part2.Service;

import java.util.Random;

public class Main {


	private static ArrayListHM<Customer> list;
	private static Random rander;
	private static SimulationHM<Customer> simu;
	private static SimulationHM<Customer> simu2;

	public static void main(String... args) {


		// PART1 TESTS
		rander = new Random();

		// runs all simulation in file
		simu = new SimulationHM<>("data1.txt", "out1.txt");
		simu.readCustormer();
		simu.simulate();


		// runs just spesific time range simulation
		simu2 = new SimulationHM<>("data2.txt", "out2.txt");
		simu2.readCustormer();
		simu2.simulate(new TimeHM(0), new TimeHM(20, 00));


		// PART2 TESTS
		try {
			Service gtu = new Service("input.txt");
			gtu.readPersons();

			System.out.println("Read : " + gtu.getTotalPersonNum() + " person from file.");
			gtu.createTable();


		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}


}
