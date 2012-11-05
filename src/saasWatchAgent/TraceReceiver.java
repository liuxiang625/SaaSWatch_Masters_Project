package saasWatchAgent;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import traceCollectors.SaasConnectivityTrace;

public class TraceReceiver implements Runnable,MessageListener, ExceptionListener {

	@Override
	public void onException(JMSException arg0) {
		System.out.println("JMS Exception occured.  Shutting down client.");
		
	}

	@Override
	public void onMessage(Message message) {
		System.out.println("Message Received!");
		String stopper = null;
		TextMessage msg = null;

		if (message instanceof TextMessage) {
			msg = (TextMessage) message;
			try {
				stopper = msg.getText();
				int a = Integer.parseInt(stopper.split("-")[0]);
				String type = stopper.split("-")[1];
				System.out.println
				("MESSAGE BEAN: Stop Message received: " 
						+ stopper + " to stop instance: " + type + " " + a);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			ObjectMessage obj = (ObjectMessage) message;
			try {
				SaasConnectivityTrace saasConnectivityTrace = null;
				if(obj.toString() != "End of transaction"){
				saasConnectivityTrace = (SaasConnectivityTrace) obj.getObject();
				System.out.println(saasConnectivityTrace.userTag);
				System.out.println("Trace connection count " + saasConnectivityTrace.connections.size());
				}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	

	@Override
	public void run() {
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			connection.setExceptionListener(this);

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("AGENT.SAASWATCH");
			System.out.println("Listen to: AGENT.SAASWATCH" );
			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);

			        consumer.close();
			        session.close();
			        connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
		
	}

}
