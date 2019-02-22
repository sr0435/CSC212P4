package edu.smith.cs.csc212.p4;

public class GameTime {
	/*
	 * gives the current hour on the game clock
	 */
	public int hour;
	/*
	 * gives the total amount of time passed
	 */
	public int totalTime;

	/*
	 * constructs the clock
	 */
	public GameTime(int hour) {
		this.hour = hour;
		this.totalTime = 0;
	}

	/*
	 * tells the player the current time
	 */
	public int getHour() {
		System.out.println("Current Time: "+ hour + ":00");
		return hour;
	}

	/*
	 * increases the hour on a 24 hour cycle
	 */
	public void increaseHour() {
		if (this.hour < 23) {
			this.hour += 1;
		} else {
			this.hour = 0;
		}
	}
	
	

}
