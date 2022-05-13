package com.kraisorn.identity.exception;

public class AccountNotFoundException extends RuntimeException{
    AccountNotFoundException(long id){
        super("Could not find account "+id);
    }
}
