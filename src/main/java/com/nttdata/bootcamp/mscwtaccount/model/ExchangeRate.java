package com.nttdata.bootcamp.mscwtaccount.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {
	
	
	@Id
    private String id;
    
	private String currencyFrom;
    
    private String currencyTo;
    
    private Double amount;

}
