package higherLower;
import java.rmi.RemoteException;

public interface IHigherLowerComponent {
	public int getInput() throws RemoteException;
	public void checkValue(int value);
}
