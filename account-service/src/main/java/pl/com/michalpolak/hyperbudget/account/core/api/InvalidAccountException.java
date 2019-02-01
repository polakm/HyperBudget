package pl.com.michalpolak.hyperbudget.account.core.api;

public class InvalidAccountException extends Exception {

    public InvalidAccountException(String message) {
        super(message);
    }
}
