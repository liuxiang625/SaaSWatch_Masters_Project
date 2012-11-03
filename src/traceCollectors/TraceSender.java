package traceCollectors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TraceSender implements Runnable {

	public SaasConnectivityTrace traceToSend;

	public TraceSender(SaasConnectivityTrace traceToSend)
	{
		this.traceToSend = traceToSend;
	}

	@Override
	public void run() {
		try {


			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("AGENT.SAASWATCH");

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// Create a messages


			ObjectMessage objMessage = session.createObjectMessage(traceToSend);
			TextMessage stopMessage = session.createTextMessage("End of transaction");
			// Tell the producer to send the message
			System.out.println("Sent message: "+ traceToSend.userTag + " : " + Thread.currentThread().getName());
			producer.send(objMessage);
			Thread.sleep(70000);
			producer.send(objMessage);
			producer.send(stopMessage);

			// Clean up
			session.close();
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}

	}

}
