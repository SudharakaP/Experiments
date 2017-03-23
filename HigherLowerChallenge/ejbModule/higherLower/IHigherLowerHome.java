package higherLower;
import java.rmi.RemoteException;
import javax.ejb.CreateException;

public interface IHigherLowerHome {
	public HigherLower create() throws CreateException, RemoteException;
}
