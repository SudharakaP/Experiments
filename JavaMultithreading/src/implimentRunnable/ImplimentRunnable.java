package implimentRunnable;

/**
 * Class that demonstrates Java multi-threading through implementing
 * the Runnable interface and passing that to the Thread class's constructor.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class ImplimentRunnable {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runner());
		Thread thread2 = new Thread(new Runner());
		
		thread1.start();
		thread2.start();
	}
}
