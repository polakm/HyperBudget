package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

public class Transaction {

    private final TransactionId id;
    private final TransactionTitle title;
    private final DateTime executionDate;
    private final Money amount;
    private final AccountId accountId;
    private final CategoryId categoryId;

    private Transaction(TransactionId id, TransactionTitle title, DateTime executionDate, Money amount, AccountId accountId, CategoryId categoryId) {
        this.id = id;
        this.title = title;
        this.executionDate = executionDate;
        this.amount = amount;
        this.accountId = accountId;
        this.categoryId = categoryId;

    }

    private Transaction(TransactionTitle title, DateTime executionDate, Money amount, AccountId accountId, CategoryId categoryId) {
        this.id = TransactionId.generate();
        this.title = title;
        this.executionDate = executionDate;
        this.amount = amount;
        this.accountId = accountId;
        this.categoryId = categoryId;

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

    public AccountId getAccountId() {
        return accountId;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public static class Builder {

        private TransactionId id;
        private TransactionTitle title;
        private DateTime executionDate;
        private Money amount;
        private AccountId accountId;
        private CategoryId categoryId;

        public Builder from(Transaction transaction) {
            this.id = transaction.id;
            this.title = transaction.title;
            this.executionDate = transaction.executionDate;
            this.amount = transaction.amount;
            this.accountId = transaction.accountId;
            this.categoryId = transaction.categoryId;
            return this;
        }

        public Builder withId(TransactionId id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(TransactionTitle title) {
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

        public Builder inCategory(CategoryId categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder forAccount(AccountId accountId) {
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
        return id.hashCode();
    }
}
