package temperature;

import javax.ejb.EJB;

public class TemperatureApplication {
	@EJB
	public static void main(String[] args) {
		TemperatureEJB converter = new TemperatureEJB();
		System.out.println(converter.convertTemperature(102));
	}
}
