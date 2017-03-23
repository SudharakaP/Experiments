package reentrantLock;

/**
 * Demonstration of ReentrantLock.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class WaitNotify {

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
				processor.secondThread();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		processor.finished();
	}
}
