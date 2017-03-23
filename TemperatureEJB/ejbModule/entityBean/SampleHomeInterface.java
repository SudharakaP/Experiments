package entityBean;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface SampleHomeInterface extends EJBHome {
	public SampleEntityBean create() throws CreateException, RemoteException;
}
