package synchronization;

import java.util.Scanner;

/**
 * Demonstration of Producer-Consumer patter with low level thread 
 * synchronization mechanisms such as using notify() and wait().
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Processors {

	public void process() throws InterruptedException {
		synchronized(this){
			System.out.println("Producer thread running...");
			wait();
			System.out.println("Resumed");
		}
	}
	
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized(this){
			
			System.out.println("Waiting for the return key....");
			scanner.nextLine();
			System.out.println("Return key pressed");
			notify();
		}
		
		scanner.close();
	}
}
