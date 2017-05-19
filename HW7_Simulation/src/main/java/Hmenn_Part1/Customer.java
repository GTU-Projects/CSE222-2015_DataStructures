package Hmenn_Part1;

/**
 * This class store customer informations
 * Every cutomer have privilage
 * Privilage 1>2>3
 * Created by hmenn on 4/26/16.
 */
public class Customer {

	private TimeHM comingTime; // customer coming time
	private TimeHM transactionTime; // customer transaction duration time
	private int type; // customer privilage

	/**
	 * Constructor
	 * @param time customer coming time
	 * @param transactionTime customer transaction duration time
	 * @param type customer privilage
	 */
	public Customer(TimeHM time, TimeHM transactionTime, int type){

		if(time == null || transactionTime ==null)
			throw new IllegalArgumentException("Time or transaction time invalid.");
		if(type<=0 || type>3)
			throw new IllegalArgumentException("Type is not in specified range");
		this.comingTime=time;
		this.transactionTime=transactionTime;
		this.type=type;
	}

	/**
	 * Return customer type/privilage
	 * @return customer type 1/2/3
	 */
	public int getType() {
		return type;
	}

	/**
	 * To String method
	 * @return return meaningful input of customer
	 */
	@Override
	public String toString(){
		return String.format(""+comingTime+"\t"+transactionTime+"\t\t" + "Customer "+type);
	}

	/**
	 * Returns total time of customer as minutes
	 * Total Time : coming time + transaction duration
	 * @return total time as minutes
	 */
	public int getTotalTime(){
		return comingTime.getTotalMin()+transactionTime.getTotalMin();
	}

	/**
	 * Returns customer coming time
	 * @return customer coming time
	 */
	public TimeHM getComingTime(){
		return this.comingTime;
	}

	/**
	 * This method return customer duration time
	 * @return transaction duration time
	 */
	public TimeHM getTransactionTime() {
		return transactionTime;
	}
}
