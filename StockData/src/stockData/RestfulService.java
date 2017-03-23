package stockData;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("generic")
public class RestfulService {

	private List<Integer> arrayList = new ArrayList<Integer>();
	
	@GET
	@Consumes("text/xml")
	@Produces("text/xml")
	public int getValue(int index) {
		return arrayList.get(index);
	}
	
	@PUT
	@Consumes("text/xml")
	public void putValue(int index, int value) {
		while (index >= arrayList.size()) {
			arrayList.add(0);
		}
		arrayList.set(index, value);
	}

	@DELETE
	@Consumes("text/xml")
	public void delete(int index) {
		arrayList.remove(index);
	}
}