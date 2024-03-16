package application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VariableValueChange {

    public static void main(String[] args) {
    	int[] initialValue = {10}; // Initial value of the variable
        
        // Display initial value
        System.out.println("Initial value: " + initialValue[0]);
        
        int newValue = 20; // New value to be set
        
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Schedule a task to change the value after a specified delay (e.g., 5 seconds)
        executorService.schedule(() -> {
        	initialValue[0] = newValue; // Change the value
            System.out.println("New value after the specified time: " + initialValue[0]);
        }, 5, TimeUnit.SECONDS); // Change the value after 5 seconds
        
        // Shutdown executor service after completion
        executorService.shutdown();
    }
}
