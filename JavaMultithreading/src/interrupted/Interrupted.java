package interrupted;

import java.util.Random;

/**
 * Demonstrating interrupting threads with the interrupt method.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Interrupted {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Starting...");
		
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				Random random = new Random();
				
				for(int i = 0; i < 1E8; i++){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interrupted!");
						break;
					}	
					Math.sin(random.nextDouble());
				}
			}
		});
		
		thread.start();
		Thread.sleep(500);
		thread.interrupt();
		thread.join();
		
		System.out.println("Finished.");

	}

}
