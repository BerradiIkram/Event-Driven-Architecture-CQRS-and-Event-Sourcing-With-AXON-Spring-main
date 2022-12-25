package com.example.es.query.service;

import com.example.es.query.queries.GetAllAccounts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.es.event.AccountCreatedEvent;
import com.example.es.query.entitie.Account;
import com.example.es.query.repository.AccountRepository;
import com.example.es.query.repository.TransactionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AccountEventHandlerService {
    private AccountRepository accountRepository ;
    private TransactionRepository transactionRepository ;


    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventEventMessage){
        log.info("Account Repository Received");
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setStatus(event.getStatus());
        account.setCreatedAt(eventEventMessage.getTimestamp());
        accountRepository.save(account);
    }
    @QueryHandler
    public List<Account> on(GetAllAccounts getAllAccounts){
        return accountRepository.findAll() ;
    }
}
