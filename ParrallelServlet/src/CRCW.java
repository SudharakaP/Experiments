
public class CRCW {

	String text = "CRCW:0";
	
	public CRCW() {
		
	}
	
	public void read() {
		System.out.println(text);
	}

	public void write(String text) {
		this.text = "CRCW:" + text;
	}

}
