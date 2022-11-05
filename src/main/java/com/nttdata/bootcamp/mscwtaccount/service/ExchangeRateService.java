package com.nttdata.bootcamp.mscwtaccount.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.bootcamp.mscwtaccount.infraestructure.ExchangeRateRepository;
import com.nttdata.bootcamp.mscwtaccount.model.ExchangeRate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeRateService {

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	public Mono<ExchangeRate> create(ExchangeRate parameter) {
		log.debug("create executed {}", parameter);
		if (parameter.getId() == null) {
			String id = UUID.randomUUID().toString();
			parameter.setId(id);
		}
		return exchangeRateRepository.save(parameter);
	}

	public Mono<ExchangeRate> delete(String parameterId) {
		log.debug("delete executed {}", parameterId);
		return exchangeRateRepository.findById(parameterId).flatMap(existingparameter -> exchangeRateRepository
				.delete(existingparameter).then(Mono.just(existingparameter)));
	}

	public Flux<ExchangeRate> findAll() {
		log.debug("findAll executed");
		return exchangeRateRepository.findAll();
	}

	public Flux<ExchangeRate> findByExchange(String from, String to) {
		log.debug("findByExchange executed {} {}", from , to);
		return exchangeRateRepository.findByExchange(from,to);
	}
}
