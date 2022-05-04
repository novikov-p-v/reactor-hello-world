package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;


public class ObservableTest {
    @Test
    void test() {
        Flux.fromIterable(Arrays.asList("Hello", "world", "!"))
                .doOnNext(System.out::println)
                .subscribe(); // (4)
    }
}
