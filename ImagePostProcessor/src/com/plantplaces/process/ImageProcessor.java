package com.plantplaces.process;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageProcessor implements Runnable {

	public static void main(String[] args) {
		// crea instancia de objeto
		Runnable ip = new ImageProcessor();
		Thread t = new Thread(ip);
		t.start();
	}

	public void run() {
		ActiveMQConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		try {
			connection = jmsConnectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue photoQueue = session.createQueue("photos");
			MessageConsumer consumer = session.createConsumer(photoQueue);

			// infinite loop that listens for messages
			while (true) {
				TextMessage message = (TextMessage) consumer.receive();
				String payload = message.getText();

				//hago la marca de agua aqui, en el consumidor.
				File file  = new File(payload);
				String ruta = "/git/PlantPlaces/WebContent/resources/images";
				BufferedImage watermark = ImageIO.read(new File(ruta, "Watermark.png"));
				Thumbnails.of(file).scale(1).watermark(Positions.BOTTOM_RIGHT, watermark, 0.9f).toFile(file);
				
				int i = 1 + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace(); 
		}
		
		if (connection != null) {
			try {
				connection.stop();
			} catch (JMSException e) { 
				e.printStackTrace();
			}
		}

	}
}
