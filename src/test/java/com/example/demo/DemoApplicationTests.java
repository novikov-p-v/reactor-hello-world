package com.example.demo;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Flow;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private TemperatureSensor temperatureSensor;

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
