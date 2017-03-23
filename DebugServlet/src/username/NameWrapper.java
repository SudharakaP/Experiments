package username;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class NameWrapper {

	private String name;
	private String address;
	private String zip;
	@SuppressWarnings("unused")
	private String outtext;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getOuttext() {
		return name + zip + address;
	}

	public void setOuttext(String outtext) {
		this.outtext = outtext;
	}
}
