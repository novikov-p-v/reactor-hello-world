package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class SimpleRestController {

    @Autowired
    private TemperatureSensor temperatureSensor;

    @GetMapping(value = "/test", produces = "text/event-stream")
    Flux<Temperature> get() {
        return temperatureSensor.temperatureStream();
    }

    @GetMapping(value = "/test1", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<A> get1() {
        return Flux.just(new A("1"), new A("1"), new A("1"))
                .delayElements(Duration.ofSeconds(2));
    }


}

class A {
    public A(String f) {
        this.f = f;
    }
    private String f;

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }
}
