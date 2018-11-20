package pl.com.michalpolak.hyperbudget.transaction.client.transaction;


import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

public class TransactionData {

    private String id;

    private String title;

    private String executionDate;

    private String dateFormat;

    private String amount;

    private String currencyCode;

    private String accountId;

    private String categoryId;

    public TransactionData(Transaction transaction) {

        setId(transaction.getId());
        setTitle(transaction.getTitle());
        setAccountId(transaction.getAccountId());
        setCategoryId(transaction.getCategoryId());

        if (transaction.getAmount() != null) {
            setAmount(transaction.getAmount().getAmount().toPlainString());
            setCurrencyCode(transaction.getAmount().getCurrencyUnit().getCode());
        }
        if (transaction.getExecutionDate() != null) {
            setExecutionDate(transaction.getExecutionDate().toString("YYYY-MM-DD"));
            setDateFormat("YYYY-MM-DD");
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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
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
}
