package entityBean;

import java.rmi.RemoteException;

public interface SampleComponentInterface extends javax.ejb.EJBObject {
	public Integer doCalculation(Integer integer) throws RemoteException;
}
