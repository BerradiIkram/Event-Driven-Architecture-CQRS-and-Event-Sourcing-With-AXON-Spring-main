package com.example.es.event;

import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent<String>{

   @Getter
   private String currency ;
    @Getter
    private double amount ;

    public AccountCreditedEvent(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }

}
