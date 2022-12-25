package com.example.es.commonapi.exceptions;

public class NegativeInitialBalanceException extends RuntimeException{
    public NegativeInitialBalanceException(String negativeBalance) {
        super(negativeBalance);
    }
}
