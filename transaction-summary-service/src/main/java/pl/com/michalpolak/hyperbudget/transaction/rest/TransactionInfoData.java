package pl.com.michalpolak.hyperbudget.transaction.rest;

import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionTypes;

public class TransactionInfoData {

    private String id;

    private String title;

    private String executionDate;

    private String amount;

    private String currencyCode;

    private String accountId;

    private String accountName;

    private String categoryId;

    private String categoryName;

    private String type;

    public TransactionInfoData(TransactionInfo transaction) {

        setId(transaction.getId());
        setTitle(transaction.getTitle());
        if(transaction.getAccount() != null) {
            setAccountId(transaction.getAccount().getId());
            setAccountName(transaction.getAccount().getName());
        }
        if(transaction.getCategory() != null) {
            setCategoryId(transaction.getCategory().getId());
            setCategoryName(transaction.getCategory().getName());
        }

        if (transaction.getAmount() != null) {
            setAmount(transaction.getAmount().getAmount().toPlainString());
            setCurrencyCode(transaction.getAmount().getCurrencyUnit().getCode());
        }

        if(transaction.getAmount() != null && transaction.getAmount().isPositive()) {
            setType(TransactionTypes.INCOME);
        }

        if(transaction.getAmount() != null && transaction.getAmount().isNegative()){
            setType(TransactionTypes.EXPENSE);
        }

        if (transaction.getExecutionDate() != null) {
            setExecutionDate(transaction.getExecutionDate().toString());
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
