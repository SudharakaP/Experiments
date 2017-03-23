package sampleMessageBean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: SampleMessageBean
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		})
public class SampleMessageBean implements MessageListener {

    /**
     * Default constructor. 
     */
    public SampleMessageBean() {
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        
    }
    
    public void setMessageDrivenContext(MessageDrivenContext mdc){
    	
    }
    
    public void ejbCreate(){}
    
    public void ejbRemove(){}

}
