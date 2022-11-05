package com.nttdata.bootcamp.mscwtaccount.infraestructure;


import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.mscwtaccount.model.ExchangeRate;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ExchangeRateRepository {
	
	private final ReactiveRedisOperations<String,  ExchangeRate> reactiveRedisOperations;

	public Mono<ExchangeRate> save(ExchangeRate documentType) {
		return this.reactiveRedisOperations.<String, ExchangeRate>opsForHash()
				.put("exchangerates", documentType.getId(), documentType).log().map(p -> documentType);
	}
	
	public Mono<ExchangeRate> delete(ExchangeRate documentType) {
		return this.reactiveRedisOperations.<String, ExchangeRate>opsForHash().remove("exchangerates", documentType.getId())
				.flatMap(p -> Mono.just(documentType));
	}
	
	public Flux<ExchangeRate> findAll() {
		return this.reactiveRedisOperations.<String, ExchangeRate>opsForHash().values("exchangerates");
	}
	
	public Flux<ExchangeRate> findByExchange(String from,String to) {
		return this.findAll().filter(p -> p.getCurrencyFrom().equals(from) && p.getCurrencyTo().equals(to));
	}
	
	public Mono<ExchangeRate> findById(String id) {
		return this.reactiveRedisOperations.<String, ExchangeRate>opsForHash().get("exchangerates", id);
	}

}
