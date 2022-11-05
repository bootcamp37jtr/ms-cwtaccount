package com.nttdata.bootcamp.mscwtaccount.application;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.mscwtaccount.model.ExchangeRate;
import com.nttdata.bootcamp.mscwtaccount.service.ExchangeRateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController {

	@Value("${spring.application.name}")
	String name;

	@Value("${server.port}")
	String port;

	@Autowired
	private ExchangeRateService exchangeRateService;

	@PostMapping
	public Mono<ResponseEntity<ExchangeRate>> create(@RequestBody ExchangeRate request) {
		log.info("create executed {}", request);
		return exchangeRateService.create(request)
				.flatMap(c -> Mono.just(ResponseEntity
						.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "DocumentType", c.getId())))
						.body(c)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/{from}/{to}")
	public Mono<ResponseEntity<Flux<ExchangeRate>>> findByCode(@PathVariable String from,
			@PathVariable String to) {
		log.info("getByName executed {} {}", from,to);
		return Mono.just(ResponseEntity.ok().body(exchangeRateService.findByExchange(from,to)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
		log.info("deleteById executed {}", id);
		return exchangeRateService.delete(id).map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping
	public Mono<ResponseEntity<Flux<ExchangeRate>>> getAll() {
		log.info("getAll executed");
		return Mono.just(ResponseEntity.ok().body(exchangeRateService.findAll()));

	}
}
