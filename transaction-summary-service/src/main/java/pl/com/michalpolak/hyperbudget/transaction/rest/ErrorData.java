package pl.com.michalpolak.hyperbudget.transaction.rest;

import java.util.Date;
import java.util.UUID;

class ErrorData {

    private final String id = UUID.randomUUID().toString();
    private final Date timestamp = new Date();
    private final String code;
    private final String title;
    private final String message;

    private ErrorData(String code, String title, String message) {

        this.code = code;
        this.title = title;
        this.message = message;
    }

    static ErrorData of(String code, String title, String message){
        return new ErrorData(code,title,message);
    }

     String getId() {
        return id;
    }

     Date getTimestamp() {
        return new Date(timestamp.getTime());
    }

     String getCode() {
        return code;
    }

     String getTitle() {
        return title;
    }

     String getMessage() {
        return message;
    }

}
