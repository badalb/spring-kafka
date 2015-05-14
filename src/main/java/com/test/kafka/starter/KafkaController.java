package com.test.kafka.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.kafka.client.ProducerConsumer;

@RestController
public class KafkaController {

	@Autowired
	private ProducerConsumer producerConsumer;

	@RequestMapping("/producer")
	public void producer() {
		System.out.println("### Starting Producer ####");
		producerConsumer.produce();
	}

	@RequestMapping("/consumer")
	public void consumer() {
		System.out.println("### Starting Consumer ####");
		producerConsumer.consume();
	}
}