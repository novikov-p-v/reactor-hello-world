package com.example.demo;

import io.reactivex.rxjava3.core.Observable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component // (1)
public class TemperatureSensor {
    private final Random rnd = new Random(); // (2)
    private final Flux<Temperature> dataStream = // (3)
            Flux
                    .range(0, 20) // (4)
                    .concatMap(tick -> Flux// (5)
                            .just(tick) // (6)
                            .delayElements(Duration.ofMillis(2000)) // (7)
                            .map(tickValue -> this.probe())) // (8)
                    .publish() // (9)
                    .refCount(); // (10)
    private Temperature probe() {
        return new Temperature(16 + rnd.nextGaussian() * 10); // (11)
    }
    public Flux<Temperature> temperatureStream() { // (12)
        return dataStream;
    }
}
