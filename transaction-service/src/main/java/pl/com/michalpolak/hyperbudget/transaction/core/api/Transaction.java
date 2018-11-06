package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import pl.com.michalpolak.hyperbudget.transaction.rest.TransactionData;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Transaction {

    public Transaction(TransactionData data){
        setId(UUID.randomUUID().toString());
        setTitle(data.getTitle());
        if(data.getAmount() != null && data.getAmount().getAmount()!= null && data.getAmount().getCurrency()!= null) {
            setAmount(Money.parse(data.getAmount().getCurrency() + " " + data.getAmount().getAmount()));
        }
        if(data.getExecutionDate() != null && data.getExecutionDate().getFormat()!= null && data.getExecutionDate().getDate()!= null) {
            setExecutionDate(DateTime.parse(data.getExecutionDate().getDate(), DateTimeFormat.forPattern(data.getExecutionDate().getFormat())));
        }

    }
    public Transaction(){
        setId(UUID.randomUUID().toString());
    }

    private String id;

    private String title;

    private DateTime executionDate;

    private Money amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(DateTime executionDate) {
        this.executionDate = executionDate;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}
