package synchronization;

/**
 * Extends the Thread class to create a new thread. Uses the 
 * volatile keyword since iterate is accessed by two threads. 
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Runner extends Thread {
	
	private volatile boolean iterate = true;

	@Override
	public void run() {
		while (iterate)
			System.out.println("My name is Suds");
	}
	
	public void shutdown(){
		iterate = false;
	}
}