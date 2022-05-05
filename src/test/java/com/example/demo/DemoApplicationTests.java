package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private TemperatureSensor temperatureSensor;

	@Autowired
	private WebTestClient webTestClient;

	private CountDownLatch countDownLatch;

	@BeforeEach
	void init() {
		countDownLatch = new CountDownLatch(1);
	}


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
		flux.subscribe(System.out::println, null, () -> {
			countDownLatch.countDown();
		});
		countDownLatch.await();
	}

	@Test
	void contextLoads() throws InterruptedException {
		var stream = temperatureSensor.temperatureStream();
		var subscriber = new Subscriber<Temperature>() {

			private Subscription s;

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				s.request(1);
			}

			@Override
			public void onNext(Temperature temperature) {
				System.out.println(temperature);
				s.request(1);
			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onComplete() {

			}
		};
		stream.subscribe(subscriber);
		System.out.println("waiting");
		Thread.sleep(20000);
	}

}
