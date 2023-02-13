package com.upgrad.appointmentservice.service;

import com.upgrad.appointmentservice.producers.KafkaMessageProducer;
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
