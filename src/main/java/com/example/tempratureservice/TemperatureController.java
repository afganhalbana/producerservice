package com.example.tempratureservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

  @Autowired
  Source source;

  @Autowired
  private TemperatureService temperatureService;

  @GetMapping("/temperature")
  ResponseEntity<TemperatureDTO> get(@RequestParam(name = "type") String type) {
    return ResponseEntity.status(201).body(temperatureService.get(type));
  }

  @PostMapping("/message")
  void message() {
    // temperatureService.getMessage("test");
    TemperatureMsgDTO temperatureMsgDTO = TemperatureMsgDTO.builder().name("test").build();
    source.output()
        .send(MessageBuilder.withPayload(temperatureMsgDTO).build());
  }
}
