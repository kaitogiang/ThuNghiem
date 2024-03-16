package application;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	static Integer myVariable = 10;
	
	public static void main(String[] args) {
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				myVariable = null;
				System.out.println("myVariable has expired");
				timer.cancel();
			}
		};
		timer.schedule(task, 5000);
		
		if (myVariable!=null) {
			System.out.println("Value of myVariable is: "+myVariable);
		} else {
			System.out.println("myVarible has expired.");
		}
	}
}
