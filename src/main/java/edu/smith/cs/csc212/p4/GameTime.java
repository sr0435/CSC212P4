package edu.smith.cs.csc212.p4;

import java.util.Random;

public class GameTime {
	
	public int hour;
	public int totalTime;
	
	public GameTime(int hour) {
		this.hour = hour;
		this.totalTime = 0;
	}
	
	public int getHour() {
		System.out.println("Current Time: "+ hour + ":00");
		//Random rand = new Random();
		//this.hour = 12;
		return hour;
	}
	
	public void increaseHour() {
		if (this.hour < 23) {
			this.hour += 1;
		} else {
			this.hour = 0;
		}
		//System.out.println(hour + ":00");
	}
	
	

}
