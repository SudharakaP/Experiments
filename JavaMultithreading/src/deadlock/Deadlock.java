package deadlock;

/**
 * This class creates two threads for the purpose of demonstrating a Deadlock.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Deadlock {

	public static void main(String[] args) throws InterruptedException {
		Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					processor.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					processor.secondThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		processor.finished();
	}
}
