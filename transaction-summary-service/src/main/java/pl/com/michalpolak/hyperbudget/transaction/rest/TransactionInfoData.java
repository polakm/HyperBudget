package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.google.gson.Gson;
import org.joda.money.Money;
import org.joda.time.DateTime;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionType;

final class TransactionInfoData {

    private final String id;
    private final String title;
    private final String executionDate;
    private final String amount;
    private final String currencyCode;
    private final String accountId;
    private final String accountName;
    private final String categoryId;
    private final String categoryName;
    private final TransactionType type;

    private TransactionInfoData(String id, String title, String executionDate, String amount, String currencyCode, String accountId, String accountName, String categoryId, String categoryName, TransactionType type) {
        this.id = id;
        this.title = title;
        this.executionDate = executionDate;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.accountId = accountId;
        this.accountName = accountName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.type = type;
    }

    String getTitle() {
        return title;
    }

    String getExecutionDate() {
        return executionDate;
    }

    String getAmount() {
        return amount;
    }

    String getCurrencyCode() {
        return currencyCode;
    }

    String getId() {
        return id;
    }

    String getAccountId() {
        return this.accountId;
    }

    String getCategoryId() {
        return categoryId;
    }

    String getAccountName() {
        return accountName;
    }

    String getCategoryName() {
        return categoryName;
    }

    TransactionType getType() {
        return type;
    }

    static Builder builder(){
        return new Builder();
    }

    static class Builder {

        private String id;
        private String title;
        private String executionDate;
        private String amount;
        private String currencyCode;
        private String accountId;
        private String accountName;
        private String categoryId;
        private String categoryName;
        private TransactionType type;

        TransactionInfoData build() {
            return new TransactionInfoData(id, title, executionDate, amount, currencyCode, accountId, accountName, categoryId, categoryName, type);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder onExecutionDate(String executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        public Builder withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
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
        public Builder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }
        public Builder withCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }
        public Builder withType(TransactionType type) {
            this.type = type;
            return this;
        }
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
