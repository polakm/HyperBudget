package pl.com.michalpolak.hyperbudget.transaction.core.api;

public class InvalidTransactionException extends Exception {

    public InvalidTransactionException(String message) {

        super(message);
    }
}
