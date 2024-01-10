package org.example.user;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);
        runProducer(IKafkaConstants.TOPIC_NAME);
        runConsumer(IKafkaConstants.TOPIC_NAME);
    }

    public static void runProducer(String topic) {
        try(Producer<Long, User> producer = ProducerUser.createProducer()) {
            User user = new User();
            user.setId("123");
            user.setName("ayman");
            producer.send(new ProducerRecord<>(topic, user));
            System.out.println("Message " + user.toString() + " sent!");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runConsumer(String topic) {
        try(Consumer<Long, User> consumer = ConsumerUser.createConsumer()) {
            consumer.subscribe(Arrays.asList(topic));
            while (true){
                ConsumerRecords<Long, User> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<Long, User> record : records){
                    System.out.println("Message Received " + record.value().toString());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
