package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;

class TransactionData {

    static class Builder {

        private String id;
        private String title;
        private String executionDate;
        private String amount;
        private String currencyCode;
        private String accountId;
        private String categoryId;
        private String type;

        TransactionData.Builder withId(String id) {
            this.id = id;
            return this;
        }

        TransactionData.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        TransactionData.Builder withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        TransactionData.Builder withExecutionDate(String executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        TransactionData.Builder withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        TransactionData.Builder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        TransactionData.Builder withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        TransactionData.Builder withType(String type) {
            this.type = type;
            return this;
        }

        TransactionData build() {
            return new TransactionData(id, title, executionDate, amount, currencyCode, accountId, categoryId, type);
        }
    }

    static Builder builder() {
        return new Builder();
    }

    private String id;
    private String title;
    private String executionDate;
    private String amount;
    private String currencyCode;
    private String accountId;
    private String categoryId;
    private String type;

    @JsonCreator
    private TransactionData(String id, String title, String executionDate, String amount, String currencyCode, String accountId, String categoryId, String type) {
        this.id = id;
        this.title = title;
        this.executionDate = executionDate;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.accountId = accountId;
        this.categoryId = categoryId;
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

    String getType() {
        return type;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
