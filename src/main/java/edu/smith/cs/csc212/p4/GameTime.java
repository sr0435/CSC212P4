package edu.smith.cs.csc212.p4;

import java.util.Random;

public class GameTime {
	
	public int hour = 12;
	
	//public GameTime(int hour) {
		//this.hour = hour;
	//}
	
	public int getHour() {
		//Random rand = new Random();
		
		return hour;
	}
	
	public void increaseHour() {
		if (this.hour < 23) {
			this.hour += hour;
		} else {
			this.hour = 0;
		}
		System.out.println(hour + ":00");
	}
	
	

}
