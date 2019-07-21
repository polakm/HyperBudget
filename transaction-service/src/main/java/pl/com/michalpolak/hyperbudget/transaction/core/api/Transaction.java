package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import java.util.Objects;
import java.util.UUID;

public class Transaction {

    private final String id;
    private final String title;
    private final DateTime executionDate;
    private final Money amount;
    private final String accountId;
    private final String categoryId;

    private Transaction(String id, String title, DateTime executionDate, Money amount, String accountId, String categoryId) {
        this.id = id;
        this.title = title;
        this.executionDate = executionDate;
        this.amount = amount;
        this.accountId = accountId;
        this.categoryId = categoryId;

    }

    private Transaction(String title, DateTime executionDate, Money amount, String accountId, String categoryId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.executionDate = executionDate;
        this.amount = amount;
        this.accountId = accountId;
        this.categoryId = categoryId;

    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getExecutionDate() {
        return executionDate;
    }

    public Money getAmount() {
        return amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public static class Builder {

        private String id;
        private String title;
        private DateTime executionDate;
        private Money amount;
        private String accountId;
        private String categoryId;

        public Builder from(Transaction transaction) {
            this.id = transaction.id;
            this.title = transaction.title;
            this.executionDate = transaction.executionDate;
            this.amount = transaction.amount;
            this.accountId = transaction.accountId;
            this.categoryId = transaction.categoryId;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder onExecutionDate(DateTime executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        public Builder withAmount(Money amount) {
            this.amount = amount;
            return this;
        }

        public Builder inCategory(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder forAccount(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Transaction build() {
            if (id == null) {
                return new Transaction(title, executionDate, amount, accountId, categoryId);
            }
            return new Transaction(id, title, executionDate, amount, accountId, categoryId);
        }

    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("title", title)
                .append("executionDate", executionDate)
                .append("amount", amount)
                .append("accountId", accountId)
                .append("categoryId", categoryId)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
