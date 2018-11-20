package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.money.Money;
import org.joda.time.DateTime;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

public class AggregatedTransaction {

    private String id;

    private String title;

    private DateTime executionDate;

    private Money amount;

    private Category category;

    private Account account;


    public AggregatedTransaction(Transaction transaction, Category category, Account account) {

        this.id = transaction.getId();
        this.title = transaction.getTitle();
        this.executionDate = transaction.getExecutionDate();
        this.amount = transaction.getAmount();
        this.category = category;
        this.account = account;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
