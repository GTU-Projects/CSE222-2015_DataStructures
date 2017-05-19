package Hmenn_Part1;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * This class writed to simulate bank queue
 * Created by hmenn on 4/29/16.
 */
public class SimulationHM<E> {
	private IPriorQueueHM<Customer> bank; // bank queue
	private String fileName; // input file name
	private String outFileName; // output file name
	private IArrayListHM<Customer> customerList; // customer list read from input file

	// to write result to output file
	private FileWriter fw;
	private BufferedWriter bw;
	private PrintWriter fileOut;


	/**
	 * COntructor
	 *
	 * @param fileName    input file name
	 * @param outFileName output file name
	 */
	public SimulationHM(String fileName, String outFileName) {

		bank = new PrioQueueHM<>(new Comparator<Customer>() {
			@Override
			public int compare(Customer customer1, Customer customer2) {
				return customer1.getType() - customer2.getType();
			}
		});

		// initialize streamers
		try {
			this.outFileName = outFileName;
			fw = new FileWriter(this.outFileName);
			bw = new BufferedWriter(fw);
			fileOut = new PrintWriter(bw);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		this.fileName = fileName;
		this.customerList = new ArrayListHM<>();
	}


	/**
	 * This method read customer from input to list
	 *
	 * @return list of customers
	 */
	public IArrayListHM<Customer> readCustormer() {

		int privilage;

		try {
			InputStream ips = new FileInputStream(fileName);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			br.readLine();

			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				StringTokenizer tokenizer = new StringTokenizer(line, ": ");
				while (tokenizer.hasMoreTokens()) {
					int hour;
					int minute;
					// read time
					hour = Integer.valueOf(tokenizer.nextToken());
					minute = Integer.valueOf(tokenizer.nextToken());
					TimeHM startTime = new TimeHM(hour, minute);
					TimeHM transactionTime = new TimeHM(Integer.valueOf(tokenizer.nextToken()));
					tokenizer.nextToken();
					privilage = Integer.valueOf(tokenizer.nextToken());
					//System.out.println("C : "+new Customer(startTime, transactionTime, privilage));
					customerList.add(new Customer(startTime, transactionTime, privilage));
					//System.out.println("L"+customerList);
				}
			}

			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return customerList; // return customer list
	}

	/**
	 * This is a helper method to simulate
	 * Takes two paremeter and runs simulation between this tow times
	 *
	 * @param startTime simulation begin time
	 * @param endTime   simulation end time
	 */
	private void simulateSpec(TimeHM startTime, TimeHM endTime) {

		int begin = startTime.getTotalMin();
		int end = endTime.getTotalMin();

		int step = customerList.get(0).getTotalTime();
		int j = 0;
		boolean stepFail = false;

		System.out.println("Left\tCome\tService\t\tType");
		fileOut.println("Left\tCome\tService\t\tType");

		for (int i = begin; i <= end; ++i) {

			// if new customer come, add queue
			if (i == customerList.get(j).getComingTime().getTotalMin()) {
				bank.enqueue(customerList.get(j));
				System.out.println("\t\t" + customerList.get(j) + " come bank ");
				fileOut.println("\t\t" + customerList.get(j) + " come bank ");


				if (stepFail) {
					step = customerList.get(j).getTotalTime();
					stepFail = false;
				}

				++j;
			}

			// if time finished, remove customer
			if (i == step) {
				Customer removed = bank.dequeue();
				TimeHM leftTime = new TimeHM(step);
				System.out.println(leftTime + "\t" + removed + " left bank ");
				fileOut.println(leftTime + "\t" + removed + " left bank ");

				// update step time with customer
				if (!bank.isEmpty()) {
					step += bank.peek().getTransactionTime().getTotalMin();
				} else stepFail = true;
			}
		}

		System.out.println("TAKING PERSONS OVER. HANDLING REMAIN CUSTOMERS");
		fileOut.println("TAKING PERSONS OVER. HANDLING REMAIN CUSTOMERS");
		while (!bank.isEmpty()) {
			Customer removed = bank.dequeue();
			step += removed.getTransactionTime().getTotalMin();
			System.out.println(new TimeHM(step) + "\t" + removed + " left bank ");
			fileOut.println(new TimeHM(step) + "\t" + removed + " left bank ");
		}

	}


	/**
	 * This is a wrapper method, simulates all customer in list
	 */
	public void simulate() {

		Customer first = customerList.get(0); // first element
		Customer last = customerList.get(customerList.size() - 1); // last element
		System.out.println("First Person came : " + first + "\nLast Persom came : " + last);
		fileOut.println("First Person came : " + first + "\nLast Persom came : " + last);
		simulateSpec(first.getComingTime(), last.getComingTime());
		fileOut.close();
	}


	/**
	 * This is a starter method. Simulate customers between two times
	 * @param startTime simulation start time
	 * @param endTime last costumer come come time
	 */
	public void simulate(TimeHM startTime, TimeHM endTime) {

		System.out.println("StartTime : " + startTime + "\n EndTime : " + endTime);
		fileOut.println("StartTime : " + startTime + "\n EndTime : " + endTime);
		simulateSpec(startTime, endTime);
		fileOut.close();
	}
}
