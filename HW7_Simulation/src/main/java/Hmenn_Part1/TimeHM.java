package Hmenn_Part1;

/**
 * This class writed to show single numbers like a real time
 *
 * 111 mins  like : 1 : 51
 * Created by hmenn on 4/28/16.
 */
public class TimeHM {

	private int hour;
	private int minute;

	/**
	 * No parameter contructor
	 */
	public TimeHM(){
		this.hour=0;
		this.minute=0;
	}

	/**
	 * One parameter contructor
	 * @param totalMinute total minute to conver hour-minute
	 */
	public TimeHM(int totalMinute) {

		hour = totalMinute / 60;
		minute = totalMinute % 60;
	}

	/**
	 * 2 parameter constuctor
	 * @param hour hour
	 * @param minute minute
	 */
	public TimeHM(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	/**
	 * To string method
	 * @return meaningfull explanation of time
	 */
	public String toString() {
		return "[" + hour + ":" + minute + "]";
	}

	/**
	 * This method converts time to minute
	 * @return time term of minutes
	 */

	public int getTotalMin() {
		return hour * 60 + minute;
	}

	/**
	 * Retursn hour
	 * @return hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Sets hour
	 * @param hour new hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * return minute
	 * @return minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Sets minute
	 * @param minute new minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
}
