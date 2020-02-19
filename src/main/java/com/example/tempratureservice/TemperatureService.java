package com.example.tempratureservice;

public interface TemperatureService {
  TemperatureDTO get(String name);
  void getMessage(String name);
}
