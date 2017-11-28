package com.mq.send;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceivingConsumer {
	
	private final static String QUEUE_NAME = "hello-task";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setHost("119.23.15.56");
		 factory.setPort(5672);
		 factory.setUsername("admin");
		 factory.setPassword("v6fqiessub");
		 Connection connection = factory.newConnection();
		 Channel channel = connection.createChannel();
		 channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		 Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		        String message = new String(body, "UTF-8");
		        System.out.println("[x] Received '" + message + "'");
		      }
		    };
		 channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
