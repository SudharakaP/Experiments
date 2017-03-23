package synchronization;

import java.util.LinkedList;

/**
 * Demonstration of Producer-Consumer patter with low level thread 
 * synchronization mechanisms such as using notify() and wait().
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class ProcessorLinkedList {
	private LinkedList<Integer> linkedList = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void process() throws InterruptedException {
		int value = 0;
		
		while(true){
			synchronized(lock){
				
				while(linkedList.size() == LIMIT){
					lock.wait();
				}
				linkedList.add(value++);
				lock.notify();
			}
		}
	}
	
	public void consume() throws InterruptedException {
		while(true){
			synchronized(lock){
				
				while(linkedList.size() == 0){
					lock.wait();
				}
				
				System.out.print("List size is: " + linkedList.size());
				int value = linkedList.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			
			Thread.sleep(1000);
		}
	}
}
