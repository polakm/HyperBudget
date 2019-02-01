package pl.com.michalpolak.hyperbudget.transaction.rest;

import java.util.Date;
import java.util.UUID;

public class ErrorData {

    private String id = UUID.randomUUID().toString();
    private Date timestamp = new Date();
    private String code;
    private String title;
    private String message;

    public ErrorData(String code, String title, String message) {

        this.code = code;
        this.title = title;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
