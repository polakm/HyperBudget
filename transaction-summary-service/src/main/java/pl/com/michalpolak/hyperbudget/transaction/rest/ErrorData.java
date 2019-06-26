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

    public String getId() {
        return id;
    }

    public Date getTimestamp() {
        return new Date(timestamp.getTime());
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

}
