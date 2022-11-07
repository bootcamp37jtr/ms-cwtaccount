package com.nttdata.bootcamp.mscwtaccount;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.bootcamp.mscwtaccount.application.CwtaccountController;
import com.nttdata.bootcamp.mscwtaccount.model.Cwtaccount;
import com.nttdata.bootcamp.mscwtaccount.model.ExchangeRate;
import com.nttdata.bootcamp.mscwtaccount.service.CwtaccountService;
import com.nttdata.bootcamp.mscwtaccount.service.ExchangeRateService;

import reactor.core.publisher.Mono;


@ExtendWith(SpringExtension.class)
@WebFluxTest(CwtaccountController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class MsCwtaccountApplicationTests {
	
	@Autowired
	 WebTestClient webTestClient;
	
	@MockBean
	 CwtaccountService cwtaccountService;
	
	@MockBean
	ExchangeRateService exchangeRateService;
	

	//@Test
	void crearTest() {
		Mono<Cwtaccount> cwtaccount = Mono.just(new Cwtaccount("4444", "99939393", "DNI", "45464",
				"MAIL@MAIL.COM",0d,"","Y"));
		when(cwtaccountService.saveAccount(cwtaccount)).thenReturn(cwtaccount);
		webTestClient.post().uri("/cwtaccount")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(cwtaccount), Cwtaccount.class)
		.exchange()
		.expectStatus().isCreated();
	}
	
	
	@Test
	void crearTest2() {
		Mono<ExchangeRate> cwtaccount = Mono.just(new ExchangeRate("2", "PEN", "USD", 20d));
		
		ExchangeRate cwtaccount2 = new ExchangeRate("2", "PEN", "USD", 20D);
		
		
		when(exchangeRateService.create(cwtaccount2)).thenReturn(cwtaccount);
		webTestClient.post().uri("/cwtaccount")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(cwtaccount), Cwtaccount.class)
		.exchange()
		.expectStatus().isCreated();
	}

}
