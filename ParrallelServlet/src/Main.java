
public class Main {
	public static void main(String[] args) {
		AccessPattern a = new AccessPattern();
		a.read();
		a.write("1");
		a.read();
		a.write("2");
		a.read();
	}
}
