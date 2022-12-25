package com.example.es.query.entitie;

import com.example.es.commonapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Data
@Entity

@AllArgsConstructor
public class Account {

    @Id
    private String id;
    private Instant createdAt;
    private  double balance ;
    private AccountStatus status ;
    private String currency ;

    @OneToMany(mappedBy = "account")
    private List<AccountTransaction> transactions;
    public Account() {
    }
}
