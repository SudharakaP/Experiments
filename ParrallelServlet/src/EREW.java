
public class EREW {

	String text = "EREW:0";
	boolean lock1 = false;
	boolean lock2 = false;
	
	public EREW() {
		
	}
	
	public void read() {
		if (!lock1){
			System.out.println(text);
			lock1 = true;
		}
	}

	public void write(String text) {
		if (!lock2){
			this.text = "EREW:" + text;
			lock2 = true;
		}
	}
}
