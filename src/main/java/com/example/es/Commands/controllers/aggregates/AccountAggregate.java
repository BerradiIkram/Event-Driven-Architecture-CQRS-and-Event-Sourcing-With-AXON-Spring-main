package com.example.es.Commands.controllers.aggregates;


import com.example.es.commonapi.commands.CreateAccountCommand;
import com.example.es.commonapi.commands.CreditAccountCommand;
import com.example.es.commonapi.commands.DebitAccountCommand;
import com.example.es.commonapi.enums.AccountStatus;
import com.example.es.commonapi.exceptions.NegativeInitialBalanceException;
import com.example.es.event.AccountCreatedEvent;
import com.example.es.event.AccountCreditedEvent;
import com.example.es.event.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String accoutId;
    private String currency;
    private double balance ;
    private AccountStatus status;

    public  AccountAggregate(){}
    @CommandHandler
    public  AccountAggregate(CreateAccountCommand command) {
        if(command.getInitialSolde()<0) throw new NegativeInitialBalanceException("Negative Balance");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getCurrency(),
                command.getInitialSolde(),
                AccountStatus.CREATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accoutId = event.getId();
        this.balance =event.getBalance() ;
        this.status =  event.getStatus();
        this.currency = event.getCurrency() ;
    }

    @CommandHandler
    public  void handle (CreditAccountCommand command){
        if(command.getAmount()<0) throw new NegativeInitialBalanceException("Negative Amount");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance = this.balance + event.getAmount() ;
    }

    @CommandHandler
    public  void handle (DebitAccountCommand command){
        if(command.getAmount()<0) throw new NegativeInitialBalanceException("Negative Amount");
        if(command.getAmount()>this.balance) throw new NegativeInitialBalanceException("Balance insufficient Exception");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance = this.balance - event.getAmount() ;
    }

}
