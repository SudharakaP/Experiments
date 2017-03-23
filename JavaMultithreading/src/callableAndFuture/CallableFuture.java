package callableAndFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Usage of Callable and Future classes to return from a thread.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class CallableFuture {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Integer> future = executor.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				if(duration > 2000){
					throw new IOException("Sleeping for too long.");
				}
				
				System.out.println("Starting...");
				
				Thread.sleep(duration);
				
				System.out.println("Finished.");
				
				return duration;
			}
			
		});
		
		executor.shutdown();
		
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException io = (IOException) e.getCause();
			
			System.out.println(io.getMessage());
		}
	}
}
