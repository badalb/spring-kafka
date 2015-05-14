package com.test.kafka.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.kafka.config.KafkaConfig;

@Service
public class ProducerConsumerImpl implements ProducerConsumer{

	@Autowired
	private Producer<Object, Object> kafkaProducer;

	@Autowired
	private KafkaConfig kafkaConfig;

	@Autowired
	private ConsumerConnector kafkaConsumer;


	public void produce() {
		int messageNo = 1;
		while (true) {
			String messageStr = new String("Message_" + messageNo);
			kafkaProducer.send(new KeyedMessage<Object, Object>(kafkaConfig
					.getTopic(), messageStr));
			messageNo++;
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void consume() {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(kafkaConfig.getTopic(), new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = kafkaConsumer
				.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = consumerMap.get(
				kafkaConfig.getTopic()).get(0);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext()){
			System.out.println(new String(it.next().message()));
		}
	}
}
