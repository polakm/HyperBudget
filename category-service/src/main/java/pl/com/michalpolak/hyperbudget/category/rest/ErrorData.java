package pl.com.michalpolak.hyperbudget.category.rest;

import java.util.Date;
import java.util.UUID;

public class ErrorData {

    private final String id = UUID.randomUUID().toString();
    private final Date timestamp = new Date();
    private final String code;
    private final String title;
    private final String message;

    public ErrorData(String code, String title, String message) {

        this.code = code;
        this.title = title;
        this.message = message;
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
