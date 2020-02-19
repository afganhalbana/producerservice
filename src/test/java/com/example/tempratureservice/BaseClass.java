package com.example.tempratureservice;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemperatureserviceApplication.class)
@AutoConfigureMessageVerifier
public abstract class BaseClass {

  @Autowired
  TemperatureController temperatureController;

  @MockBean
  TemperatureService temperatureService;

  @Autowired
  Source source;

  TemperatureDTO temperatureDTO;

  TemperatureDTO generateTemperatureDTO() {
    return TemperatureDTO.builder().unitName("c").temprature(1).build();
  }

  @Before
  public void setup() {
    initMocks(this);
    temperatureDTO = this.generateTemperatureDTO();

    RestAssuredMockMvc.standaloneSetup(temperatureController);

    when(temperatureService.get(anyString())).thenReturn(temperatureDTO);
  }

  public void triggerMessage() {
    TemperatureMsgDTO temperatureMsgDTO = TemperatureMsgDTO.builder().name("cantik").build();
    source.output().send(MessageBuilder.withPayload(temperatureMsgDTO).build());
  }
}
