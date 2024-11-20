package org.example;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class App {
    private final static String QUEUE_NAME = "Simulator";


    public static void main( String[] args ) {




        String filePath="C:/Users/wolf/Downloads/sensor.csv";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("kangaroo.rmq.cloudamqp.com");
        factory.setPort(5672);
        factory.setUsername("kjuqsuhe");
        factory.setPassword("IJXZhQ3ktm19dUaKxcRQMTMjsjKPYHHR");
        factory.setVirtualHost("kjuqsuhe");

        String[] ids= {"505d400e-a15d-4670-9c23-93a2dd18464d"};
        int id=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line here



                String ret="{\"timestamp\":\"" + System.currentTimeMillis() + "\", \"device_id\":\"" + ids[id] + "\"" +
                        ", \"measurement_value\":\"" + line + "\"" +
                        "}";

                try (Connection connection = factory.newConnection();
                     Channel channel = connection.createChannel()) {
                    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
                    String message = "Hello World!";
                    channel.basicPublish("", QUEUE_NAME, null, ret.getBytes());
                    System.out.println(" [x] Sent '" + ret + "'");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (TimeoutException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
