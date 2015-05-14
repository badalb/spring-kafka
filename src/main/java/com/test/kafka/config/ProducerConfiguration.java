package com.test.kafka.config;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfiguration {

	@Autowired
	private KafkaConfig kafkaConfig;

	@Bean (name= "kafkaProduce")
	public Producer<Object, Object> producer() {
		Properties props = new Properties();
		props.put("serializer.class", kafkaConfig.getSerializerClass());
		props.put("metadata.broker.list", kafkaConfig.getBrokerAddress());
		return new kafka.javaapi.producer.Producer<Object, Object>(
				new ProducerConfig(props));
	}

}
