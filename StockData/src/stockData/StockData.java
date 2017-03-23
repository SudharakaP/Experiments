package stockData;

import javax.jws.WebService;

@WebService
public class StockData {
	
	public StockData(){}

	public String HelloWorld(String s){
		return "Hello " + s;
	}

	public String HelloWorld2(String s){
		return "Hello2 " + s;
	}
}
