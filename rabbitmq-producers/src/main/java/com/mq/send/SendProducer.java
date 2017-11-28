package com.mq.send;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @author zengzp
 * @date 2017年9月1日 下午3:45:17
 * @describtion 消息发送者
 * @version 1.0
 */
public class SendProducer {

	 private final static String QUEUE_NAME = "hello-task";
	 
	 public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setHost("119.23.15.56");
		 factory.setPort(5672);
		 factory.setUsername("admin");
		 factory.setPassword("v6fqiessub");
		 Connection connection = factory.newConnection();
		 Channel channel = connection.createChannel();
		 channel.exchangeDeclare("logs", "fanout");
//		 channel.queueDeclare(QUEUE_NAME,true,false,false,null);
		 String msgContent = "Hello World";
		 for (int i = 0; i < 5; i++) {
			 channel.basicPublish("logs", "", MessageProperties.PERSISTENT_TEXT_PLAIN,msgContent.getBytes());
			 Thread.sleep(500);
		 }
		 channel.close();
		 connection.close();
	 }
}
