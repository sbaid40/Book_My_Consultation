package com.upgrad.ratingservice.service;

import java.io.IOException;

public interface MessageService {

  public void produceMessage(String topicName, String key, String value) throws IOException;
}