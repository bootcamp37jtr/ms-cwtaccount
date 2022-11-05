package com.nttdata.bootcamp.mscwtaccount.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cwtaccount {
	
	@Id
	private String id;
	private String cellphoneNumber;
	private String typeDocument;
	private String numberDocument;
	private String email; 
	private Double availableBalance; 
	private String numberCard; 
	private String paymentType;
}
