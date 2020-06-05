package com.moim.user.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.moim.kafka.EventUser;

/**
 * Sender.java
 * 
 * @author cdssw
 * @since 2020. 6. 5.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 6. 5.    cdssw            최초 생성
 * </pre>
 */
@Component
public class Sender {

	@Autowired
	private KafkaTemplate<String, EventUser> kafkaTemplate;
	
	public void send(String topic, EventUser payload) {
		kafkaTemplate.send(topic, payload);
	}
}
