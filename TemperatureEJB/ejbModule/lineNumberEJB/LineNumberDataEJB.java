package lineNumberEJB;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * Session Bean implementation class LineNumberDataEJB
 */
public class LineNumberDataEJB implements EntityBean {

	private static final long serialVersionUID = 6510934195242351503L;
	private List<String> data;	

	/**
     * Default constructor. 
     */
    public LineNumberDataEJB() {
    	
    }

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
		Path p = FileSystems.getDefault().getPath("Data Source.txt");
		Charset c = Charset.defaultCharset();
		try {
			data = Files.readAllLines(p, c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbRemove() throws RemoveException, EJBException, RemoteException {
		
	}

	@Override
	public void ejbStore() throws EJBException, RemoteException {
		Path p = FileSystems.getDefault().getPath("Data Source.txt");
		Charset c = Charset.defaultCharset();
		try {
			Files.write(p, data, c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setEntityContext(EntityContext arg0) throws EJBException, RemoteException {
		
	}

	@Override
	public void unsetEntityContext() throws EJBException, RemoteException {
		
	}
	
	public void modifyData(int line, String newData){
		data.set(line, newData);
	}
}
