package com.nttdata.bootcamp.mscwtaccount.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.mscwtaccount.kafka.model.OperationSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OperationProducer {
	private final KafkaTemplate<String, OperationSender> kafkaTemplate;

	
	@Value(value = "${kafka.topic.bootcoin.name}")
	private String topic;

	public void sendMessage(OperationSender balanceModel) {

		ListenableFuture<SendResult<String, OperationSender>> future = kafkaTemplate.send(this.topic, balanceModel);

		future.addCallback(new ListenableFutureCallback<SendResult<String, OperationSender>>() {
			@Override
			public void onSuccess(SendResult<String, OperationSender> result) {
				log.info("Message {} has been sent ", balanceModel);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Something went wrong with the balanceModel {} ", balanceModel);
			}
		});
	}
	
	public void sendMessage(OperationSender balanceModel,String topic) {

		ListenableFuture<SendResult<String, OperationSender>> future = kafkaTemplate.send(topic, balanceModel);

		future.addCallback(new ListenableFutureCallback<SendResult<String, OperationSender>>() {
			@Override
			public void onSuccess(SendResult<String, OperationSender> result) {
				log.info("Message {} has been sent ", balanceModel);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Something went wrong with the balanceModel {} ", balanceModel);
			}
		});
	}
}
