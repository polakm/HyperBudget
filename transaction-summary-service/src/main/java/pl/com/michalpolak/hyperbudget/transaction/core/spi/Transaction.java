package pl.com.michalpolak.hyperbudget.transaction.core.spi;


import org.joda.money.Money;
import org.joda.time.DateTime;

public class Transaction {

    private String id;

    private String title;

    private DateTime executionDate;

    private Money amount;

    private String categoryId;

    private String accountId;


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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
