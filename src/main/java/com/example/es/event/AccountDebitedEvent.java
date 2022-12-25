package com.example.es.event;

import lombok.Getter;

public class AccountDebitedEvent extends BaseEvent<String>{

   @Getter
   private String currency ;
    @Getter
    private double amount ;

    public AccountDebitedEvent(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }

}
