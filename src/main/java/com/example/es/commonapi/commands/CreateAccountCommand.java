package com.example.es.commonapi.commands;

import lombok.Getter;

public class CreateAccountCommand extends  BaseCommand<String>{
    @Getter
    private  String currency ;
    @Getter private  double initialSolde;
    public CreateAccountCommand(String id, String currency, double initialSolde) {
        super(id);
        this.currency = currency;
        this.initialSolde = initialSolde;
    }
}
