package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class Producer2 {

    public static void main(String[] args) {
        String server = "localhost:9092";
        String topic = "first_topic";
        String message = "hello world!";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        String[] users = {"ayman", "mouad", "kawkab", "anas", "goal"};
        String[] items = {"book", "alarm clock", "t-shirts", "gift card"};
        try(final Producer<String, String> producer = new KafkaProducer<>(properties)) {
            final Random rnd = new Random();
            final Long numMessages = 10L;
            for (Long i = 0L; i < numMessages; i++){
                String user = users[rnd.nextInt(users.length)];
                String item = items[rnd.nextInt(items.length)];

                producer.send(
                        new ProducerRecord<>(topic, user, item),
                        (event, ex) -> {
                            if (ex != null)
                                ex.printStackTrace();
                            else
                                System.out.printf("Produced event to topic %s: key = %-10s value = %s%n", topic, user, item);
                        }
                );
            }
            System.out.printf("%s events were produced to topic %s%n", numMessages, topic);
        }
        }
    }
