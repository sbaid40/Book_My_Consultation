package com.upgrad.ratingservice.service;

import com.upgrad.ratingservice.producers.KafkaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer ;

    //method to send data to Kafka
    @Override
    public void produceMessage(String topicName, String key, String value) throws IOException {
        kafkaMessageProducer.publish(topicName, key, value);
    }
}
