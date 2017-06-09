package com.onlineBankingApplication.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	@Autowired
	EmailService emailService;
	

    @JmsListener(destination = "${jms.mail.queue}", containerFactory = "containerFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }
    
    @JmsListener(containerFactory = "containerFactory",
                 destination = "${jms.mail.queue}",
                 selector = "Operation = 'Create'")
    public void processCreateemailMessage(Email email) throws JMSException{
        emailService.createNew(email);
    }

    @JmsListener(containerFactory = "containerFactory",
                 destination = "${jms.mail.queue}",
                 selector = "Operation = 'Update'")
    public void processUpdateemailMessage(Email email) throws JMSException{
        emailService.update(email);
    }

    @JmsListener(containerFactory = "containerFactory",
                 destination = "${jms.mail.queue}",
                 selector = "Operation = 'Delete'")
    public void processDeleteemailMessage(Email email) throws JMSException{
        emailService.delete(email);
    }

}
