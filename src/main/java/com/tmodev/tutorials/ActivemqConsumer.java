package com.tmodev.tutorials;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 
 * @author @tshenolo
 * @version 1.0
 *
 */
public class ActivemqConsumer {
	
	private static String activemqhost = "failover:(tcp://localhost:61616)?timeout=1000";
	private static String activemquser = "";
	private static String activemqpass = "";
	private static String QueueName = "QueueName";
	
	/**
	 * Consumes a message from a queue.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		
		try {
		
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(activemquser,activemqpass, activemqhost);
	        connection = factory.createConnection();
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        connection.start();
	        Destination destination = session.createQueue(QueueName); // change queue name
	        consumer = session.createConsumer(destination);        
	        Message message = consumer.receive();
			
	        if (message instanceof TextMessage) {       	
	        	TextMessage textMessage = (TextMessage) message;
	        	System.out.println("Received message: " + textMessage.getText());   // Received mesage      	
	        } else {        	
	        	System.out.println("Received message: " + message);					// Received mesage  
	        }	
        
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			consumer.close();
	        session.close(); 
	        connection.close();
		}        
        
	}

}
