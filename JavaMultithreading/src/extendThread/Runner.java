package extendThread;

/**
 * Extends the Thread class and overrides the run method. 
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Runner extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println(i+ ") My name is Suds" );
	}
}
