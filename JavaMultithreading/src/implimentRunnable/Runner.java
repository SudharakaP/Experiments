package implimentRunnable;

/**
 * Implements the Runnable interface
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Runner implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println("Count " + i);
	}
}
