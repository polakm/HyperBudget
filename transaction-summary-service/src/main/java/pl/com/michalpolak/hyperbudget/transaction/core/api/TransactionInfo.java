package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

public class TransactionInfo {

    private String id;

    private String title;

    private DateTime executionDate;

    private Money amount;

    private Category category;

    private Account account;

    public TransactionInfo(){

    }

    public TransactionInfo(Transaction transaction, Category category, Account account) {

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


    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("title", title)
                .append("executionDate", executionDate)
                .append("amount", amount)
                .append("category", category)
                .append("account", account)
                .toString();
    }
}
