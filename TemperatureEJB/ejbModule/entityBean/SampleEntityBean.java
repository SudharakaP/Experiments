package entityBean;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Session Bean implementation class SampleEntityBean
 */

public class SampleEntityBean implements EntityBean {
	
	private static final long serialVersionUID = 8490032528884794870L;
	
	@Id
	@TableGenerator(name = "")
	@Column()
	Integer key;
	
	public Integer getID() {
		return key;
	}

    /**
     * Default constructor. 
     */
    public SampleEntityBean() {

    }

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbRemove() throws RemoveException, EJBException,
			RemoteException {
		
	}

	@Override
	public void ejbStore() throws EJBException, RemoteException {
		
	}

	@Override
	public void setEntityContext(EntityContext arg0) throws EJBException,
			RemoteException {
		
	}

	@Override
	public void unsetEntityContext() throws EJBException, RemoteException {

		
	}
}
