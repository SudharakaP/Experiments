package extendThread;

/**
 * Class that demonstrates multi-threading through extending the 
 * thread class.
 * 
 * @author Sudharaka Palamakumbura
 *
 */

public class ExtendThread {
	public static void main(String[] args) {
		Runner multiThread1 = new Runner();
		multiThread1.start();
		
		Runner multiThread2 = new Runner();
		multiThread2.start();
	}
}
