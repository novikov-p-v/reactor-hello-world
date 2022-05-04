package com.example.demo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class SimpleRestController {

    @Autowired
    private TemperatureSensor temperatureSensor;
    @Autowired
    private MongoDbRepository mongoDbRepository;

    @GetMapping(value = "/test", produces = "text/event-stream")
    Flux<Temperature> get() {
        return temperatureSensor.temperatureStream();
    }
}
