package fibonacci;

import javax.jws.WebService;

/**
 * Generates the nth number of the Fibonacci sequence.
 * 
 * @author Sudharaka
 *
 */
@WebService
public class FibonacciSequence {
	public FibonacciSequence(){}
	
	public int NthFibonacciTerm(int n){
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else if (n >= 2)
			return NthFibonacciTerm(n - 1) + NthFibonacciTerm(n - 2);
		else
			return -1;	
	}
}
