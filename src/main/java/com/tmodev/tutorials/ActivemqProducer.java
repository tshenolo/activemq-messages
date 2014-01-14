package com.tmodev.tutorials;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 
 * @author @tshenolo
 * @version 1.0
 *
 */
public class ActivemqProducer {
	
	private static String activemqhost = "failover:(tcp://localhost:61616)?timeout=1000";
	private static String activemquser = "";
	private static String activemqpass = "";
	private static String QueueName = "QueueName"; // change queue name

	/**
	 * Sends a message to a queue or topic.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		
		try {
        
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(activemquser,activemqpass, activemqhost);
	        connection = factory.createConnection();
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        connection.start();
	        Queue queue = session.createQueue(QueueName); 
	        producer = session.createProducer(queue);  
	        
	        String message = "message goes here";  // Change message
	        
	        TextMessage txtMessage = null;
	        txtMessage = session.createTextMessage(message); 
 	        producer.send(txtMessage);
	    	Thread.sleep(1000); 
	        
	        System.out.println("message sent: " + message);
	    	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			producer.close(); 
	        session.close(); 
	        connection.close();
		}
        
   }
}
