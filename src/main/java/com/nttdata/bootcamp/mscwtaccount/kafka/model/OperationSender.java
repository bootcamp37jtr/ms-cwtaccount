package com.nttdata.bootcamp.mscwtaccount.kafka.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationSender {
	
	private String idaccount;
	private String typeAction;
	private String typeOperation;  
	private Double amount;
}
