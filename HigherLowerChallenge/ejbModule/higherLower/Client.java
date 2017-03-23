package higherLower;

import java.io.IOException;

import javax.ejb.EJB;

public class Client {
	@EJB
	public static void main(String[] args) throws IOException {
		HigherLower h = new HigherLower();
		int i = h.getInput();
		while (!h.checkValue(i))
			i = h.getInput();
	}
}
