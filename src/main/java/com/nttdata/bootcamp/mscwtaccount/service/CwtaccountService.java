package com.nttdata.bootcamp.mscwtaccount.service;

import com.nttdata.bootcamp.mscwtaccount.model.Cwtaccount;

import reactor.core.publisher.Mono;

public interface CwtaccountService {
	Mono<Cwtaccount> saveAccount(Mono<Cwtaccount> mobileWalletAccount);
	
	Mono<Cwtaccount> findById(String id);
}
