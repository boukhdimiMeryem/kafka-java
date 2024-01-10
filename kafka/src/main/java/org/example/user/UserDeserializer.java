package org.example.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class UserDeserializer implements Deserializer<User> {
    @Override
    public User deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        User object = null;
        try {
            object = mapper.readValue(data, User.class);
        } catch (Exception exception){
            System.out.println("Error in serializing object " + data);
        }
        return object;
    }
}
