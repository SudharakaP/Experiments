package reentrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Demonstration of ReentrantLock.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Processor {
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment(){
		for(int i = 0; i < 1000; i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();
		
		System.out.println("Waiting...");
		cond.await();
		
		System.out.println("Woken up!");
		
		try{
			increment();
		}finally{
			lock.unlock();
		}
	}
	
	@SuppressWarnings("resource")
	public void secondThread(){
		lock.lock();
		
		System.out.println("Hit Return...");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		cond.signal();
		
		try{
			increment();
		}finally{
			lock.unlock();
		}
	}
	
	public void finished(){
		System.out.println("Count is: " + count);
	}
}
