package com.upgrad.userservice.service;

import com.upgrad.userservice.producers.KafkaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private KafkaMessageProducer kafkaMessageProducer ;


  @Override
  public void produceMessage(String topicName, String key, String value) throws IOException {
    kafkaMessageProducer.publish(topicName, key, value);
  }
}
