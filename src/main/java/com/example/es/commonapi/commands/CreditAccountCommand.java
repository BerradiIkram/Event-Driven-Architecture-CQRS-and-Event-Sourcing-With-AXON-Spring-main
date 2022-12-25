package com.example.es.commonapi.commands;

import lombok.Getter;

public class CreditAccountCommand extends  BaseCommand<String>{
    @Getter
    private  String currency ;
    @Getter private  double amount;
    public CreditAccountCommand(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
