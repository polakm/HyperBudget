package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.google.gson.Gson;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionType;

final class TransactionInfoData {

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

     String getTitle() {
        return title;
    }

     void setTitle(String title) {
        this.title = title;
    }

     String getExecutionDate() {
        return executionDate;
    }

     void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

     String getAmount() {
        return amount;
    }

     void setAmount(String amount) {
        this.amount = amount;
    }

     String getCurrencyCode() {
        return currencyCode;
    }

     void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

     String getId() {
        return id;
    }

     void setId(String id) {
        this.id = id;
    }

     String getAccountId() {
        return this.accountId;
    }

     void setAccountId(String accountId) {
        this.accountId = accountId;
    }

     String getCategoryId() {
        return categoryId;
    }

     void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

     String getAccountName() {
        return accountName;
    }

     void setAccountName(String accountName) {
        this.accountName = accountName;
    }

     String getCategoryName() {
        return categoryName;
    }

     void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

     TransactionType getType() {
        return type;
}

     void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
