package synchronization;

/**
 * Demonstration of Producer-Consumer patter with low level thread 
 * synchronization mechanisms such as using notify() and wait().
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class WaitNotifyLinkedList {

	public static void main(String[] args) throws InterruptedException {
		ProcessorLinkedList processor = new ProcessorLinkedList();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					processor.process();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}
