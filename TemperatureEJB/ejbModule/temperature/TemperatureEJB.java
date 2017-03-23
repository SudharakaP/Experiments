package temperature;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class TemperatureEJB
 */
@Stateless
@LocalBean
public class TemperatureEJB implements SessionBean {

	private static final long serialVersionUID = -3129078661080437702L;

	/**
     * Default constructor. 
     */
    public TemperatureEJB() {

    }
    
    public double convertTemperature(double celsiusTemp){
    	return (celsiusTemp * 1.8) + 32;
    }

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		
	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
		
	}
}
