package com.nttdata.bootcamp.mscwtaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.mscwtaccount.infraestructure.CwtaccountRepository;
import com.nttdata.bootcamp.mscwtaccount.model.Cwtaccount;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CwtaccountServiceImpl implements  CwtaccountService {
	
	@Autowired
	CwtaccountRepository mobileWalletAccountRepository;
	
	@Override
	public Mono<Cwtaccount> saveAccount(Mono<Cwtaccount> mobileWalletAccount) {
		log.debug("dato",mobileWalletAccount);
		return mobileWalletAccount.flatMap(mwa ->{
			if(mwa.getAvailableBalance() == null) {
				mwa.setAvailableBalance(0d);
			}
			return mobileWalletAccountRepository.save(mwa);
		});
	}

	@Override
	public Mono<Cwtaccount> findById(String id) {
		return mobileWalletAccountRepository.findById(id)	;
	}

}
