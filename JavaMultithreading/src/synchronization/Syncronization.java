package synchronization;

import java.util.Scanner;

/**
 * Usage of the keyword "volatile" for a shared resource
 * so as to prevent unintended behaviour.
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class Syncronization {

	public static void main(String[] args) {
		
		Runner thread = new Runner();
		thread.start();
		
		Scanner input = new Scanner(System.in);
		input.hasNextLine();
		thread.shutdown();
		input.close();
	}	
}

