package implimentRunnable;

/**
 * Same as the ImplimentRunnable class but this time we use
 * an anonymous class.
 *
 * @author Sudharaka Palamakumbura
 *
 */
public class AltImplimentRunnable {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable(){
			public void run() {
				for (int i = 0; i < 10; i++)
					System.out.println(i+ ") My name is Suds" );
			}
		});

		Thread thread2 = new Thread(new Runnable(){
			public void run() {
				for (int i = 0; i < 20; i++)
					System.out.println(i+ ") My name is Suds" );
			}
		});

		thread1.start();
		thread2.start();
	}
}