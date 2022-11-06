package com.nttdata.bootcamp.mscwtaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.bootcamp.mscwtaccount.application.CwtaccountController;
import com.nttdata.bootcamp.mscwtaccount.service.CwtaccountService;


@ExtendWith(SpringExtension.class)
@WebFluxTest(CwtaccountController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class MsCwtaccountApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private CwtaccountService cwtaccountService;
	
	private 

	@Test
	void contextLoads() {
	}

}
