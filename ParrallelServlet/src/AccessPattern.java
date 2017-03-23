
public class AccessPattern {
	CRCW crcw = new CRCW();
	CREW crew = new CREW();
	EREW erew = new EREW();
	
	public AccessPattern() {
		
	}
	
	public void read() {
		crcw.read();
		crew.read();
		erew.read();
	}
	
	public void write(String text) {
		crcw.write(text);
		crew.write(text);
		erew.write(text);
	}

}
