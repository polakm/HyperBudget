package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

import java.util.Objects;

public class TransactionInfo {

    private TransactionId id;
    private TransactionTitle title;
    private DateTime executionDate;
    private Money amount;
    private Category category;
    private Account account;

    private TransactionInfo(Transaction transaction, Category category, Account account) {
        if(transaction!=null){
            this.id = TransactionId.fromString(transaction.getId());
            this.title = TransactionTitle.fromString(transaction.getTitle());
            this.executionDate = transaction.getExecutionDate();
            this.amount = transaction.getAmount();
        }
        this.category = category;
        this.account = account;
    }

    public static TransactionInfo of(Transaction transaction, Category category, Account account) {
        return new TransactionInfo(transaction, category, account);
    }

    public static Builder builder() {
        return new Builder();
    }


    public TransactionId getId() {
        return id;
    }

    public TransactionTitle getTitle() {
        return title;
    }

    public DateTime getExecutionDate() {
        return executionDate;
    }

    public Money getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public Account getAccount() {
        return account;
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {

        private Transaction transaction;
        private Category category;
        private Account account;

        public Builder withTransaction(Transaction transaction) {
            this.transaction = transaction;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withAccount(Account account) {
            this.account = account;
            return this;
        }

        public TransactionInfo build() {
            return new TransactionInfo(transaction, category, account);
        }
    }
}
