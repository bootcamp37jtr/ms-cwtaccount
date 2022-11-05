
package com.nttdata.bootcamp.mscwtaccount.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.mscwtaccount.kafka.model.Cwtransaction;
import com.nttdata.bootcamp.mscwtaccount.kafka.model.OperationSender;
import com.nttdata.bootcamp.mscwtaccount.service.CwtaccountService;
import com.nttdata.bootcamp.mscwtaccount.service.ExchangeRateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class OperationConsumer {

	@Autowired
	CwtaccountService mobileWalletAccountService;
	
	@Autowired
	private OperationProducer operationProducer;
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	@KafkaListener(topics = "${kafka.topic.bootcoin.name}")
	public void listener(@Payload Cwtransaction operation) {
		log.debug("Message received : {} ", operation);
		updateBalances(operation).block();
	}
	
	
	private Mono<OperationSender> updateBalances(Cwtransaction operation) {
		log.info("ENTROOOOOOOOOOOOOO");
		return mobileWalletAccountService.findById(operation.getIdaccountbuyer()).flatMap(buyer -> {
			log.info("updating account ...",buyer.getCellphoneNumber());
			buyer.setAvailableBalance(buyer.getAvailableBalance() + operation.getAmount());
			operation.setAccount(buyer);
			return mobileWalletAccountService.saveAccount(Mono.just(buyer));
		}).flatMap(sendB->{
			return this.buildOperation(Mono.just(operation), "A");
		}).flatMap(buyer ->{
			return mobileWalletAccountService.findById(operation.getIdaccountseller()).flatMap(seller -> {
				log.info("updating account ...",seller.getCellphoneNumber());
				seller.setAvailableBalance(seller.getAvailableBalance() - operation.getAmount());
				operation.setAccount(seller);
				return mobileWalletAccountService.saveAccount(Mono.just(seller));
			});
		}).flatMap(send ->{
			return this.buildOperation(Mono.just(operation), "R");
		});
	}
	
	private Mono<OperationSender> buildOperation(Mono<Cwtransaction> operation,String typeAction){
		return operation.flatMap(tx->{
			OperationSender operationSender = new OperationSender();
			operationSender.setTypeOperation("BOOTCOIN");
			operationSender.setTypeAction(typeAction);
			
			if(tx.getAccount().getPaymentType().equalsIgnoreCase("Y")) {
				operationSender.setIdaccount(tx.getAccount().getCellphoneNumber());
				//return   Mono.just(this.sendOperation(operationSender,"yunki-topic"));
				return   this.getExchange(tx.getAmount()).flatMap(m->{
					operationSender.setAmount(m*tx.getAmount());
					return   Mono.just(this.sendOperation(operationSender,"yunki-topic"));
				});
			} else{
				operationSender.setAmount(tx.getAmount());
				operationSender.setIdaccount(tx.getAccount().getNumberCard());
				return Mono.just(this.sendOperation(operationSender,"account-topic"));
			}
		});
	}
	
	
	private OperationSender sendOperation(OperationSender operation,String topic) {
		log.debug("sendOperation executed {}", operation);
		if (operation != null) {
			operationProducer.sendMessage(operation,topic);
		}
		return operation;
	}
	
	private Mono<Double> getExchange(Double amount) {
	return	exchangeRateService.findByExchange("BIT", "PEN").map(ex->{
			return ex.getAmount();
		}).collectList().flatMap(list->{
			return Mono.just(list.stream().findFirst().get());
		});
	}
	
	
	
}
