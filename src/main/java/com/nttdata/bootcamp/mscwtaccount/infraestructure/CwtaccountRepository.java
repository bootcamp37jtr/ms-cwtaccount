package com.nttdata.bootcamp.mscwtaccount.infraestructure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.mscwtaccount.model.Cwtaccount;

@Repository
public interface CwtaccountRepository extends ReactiveMongoRepository<Cwtaccount, String> {

}
