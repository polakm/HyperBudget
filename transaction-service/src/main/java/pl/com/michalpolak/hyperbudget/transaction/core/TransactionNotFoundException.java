package pl.com.michalpolak.hyperbudget.transaction.core;

import java.text.MessageFormat;

public class TransactionNotFoundException extends Exception {

    TransactionNotFoundException(String id){
        super(MessageFormat.format("Transaction with id \"{0}\" not found", id));
    }





}
