package com.nttdata.bootcamp.mscwtaccount.kafka.model;



import com.nttdata.bootcamp.mscwtaccount.model.Cwtaccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cwtransaction {
	
	private String id;
	private String idaccountbuyer;
	private String paymentType;  // tipo de operacion ABONO, RETIRO
	private Double amount;
	private String idaccountseller;
	
	private Cwtaccount account;
	
}
