package org.example.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class UserSerializer implements Serializer<User> {

    @Override
    public byte[] serialize(String topic, User data) {
        byte[] userByte = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userByte = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception){
            System.out.println("Error in serializing object " + data);
        }
        return userByte;
    }
}
