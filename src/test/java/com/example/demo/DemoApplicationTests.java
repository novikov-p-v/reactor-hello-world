package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class DemoApplicationTests {

	@Autowired
	private TemperatureSensor temperatureSensor;

	@Autowired
	private WebTestClient webTestClient;


	@Test
	void testTemperature() throws InterruptedException {
		var r = webTestClient
				.get()
				.uri("/getBooksReactive")
				.exchange()
				.expectStatus()
				.isOk()
				.returnResult(Book.class);
		var flux = r.getResponseBody();
		StepVerifier.create(flux.log())
				.assertNext(book -> assertNotNull(book.getId()))
				.expectNextCount(10)
				.expectComplete()
				.verify();
	}

	@Test
	void contextLoads() throws InterruptedException {
		var stream = temperatureSensor.temperatureStream();

		StepVerifier.create(temperatureSensor.temperatureStream().log())
				.expectSubscription()
				.expectNextCount(20)
				.expectComplete()
				.verify();
	}

}
