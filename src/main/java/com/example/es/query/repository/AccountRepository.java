package com.example.es.query.repository;

import com.example.es.query.entitie.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
