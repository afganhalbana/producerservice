package com.example.tempratureservice;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TemperatureServiceImpl implements TemperatureService {

  @Autowired
  private Source source;

  @Override
  public TemperatureDTO get(String name) {
    Random random = new Random();
    float temperature = random.nextInt(100);
    return TemperatureDTO.builder().temprature(temperature).unitName(name).build();
  }

  @Override
  public void getMessage(String name) {
    TemperatureMsgDTO temperatureMsgDTO = TemperatureMsgDTO.builder().name(name).build();
    source.output()
        .send(MessageBuilder.withPayload(temperatureMsgDTO).build());
  }
}
