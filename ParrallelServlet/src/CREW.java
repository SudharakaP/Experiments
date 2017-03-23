
public class CREW {

	String text = "CREW:0";
	boolean lock = false;
	
	public CREW() {
		
	}
	
	public void read() {
		if (!lock){
			System.out.println(text);
		}
	}

	public void write(String text) {
		if (!lock){
			this.text = "CREW:" + text;
			lock = true;
		}
	}
}
