package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SimpleRestController {

    @Autowired
    private TemperatureSensor temperatureSensor;

    @GetMapping(value = "/test", produces = "text/event-stream")
    Flux<Temperature> get() {
        return temperatureSensor.temperatureStream();
    }
}
