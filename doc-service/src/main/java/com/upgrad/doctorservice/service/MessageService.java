package com.upgrad.doctorservice.service;

import java.io.IOException;


/**
 * Message service contract
 */
public interface MessageService {

  public void produceMessage(String topicName, String key, String value) throws IOException;
}
