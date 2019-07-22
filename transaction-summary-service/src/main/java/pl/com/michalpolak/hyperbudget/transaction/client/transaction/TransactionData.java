package pl.com.michalpolak.hyperbudget.transaction.client.transaction;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;

class TransactionData {

    private final String id;
    private final String title;
    private final String executionDate;
    private final String amount;
    private final String currencyCode;
    private final String accountId;
    private final String categoryId;

    @JsonCreator
    private TransactionData(Transaction transaction) {

        this.id = transaction.getId();
        this.title = transaction.getTitle();
        this.accountId = transaction.getAccountId();
        this.categoryId = transaction.getCategoryId();

        if (transaction.getAmount() != null) {
            this.amount = transaction.getAmount().getAmount().toPlainString();
            this.currencyCode = transaction.getAmount().getCurrencyUnit().getCode();
        }else{
            this.amount = null;
            this.currencyCode = null;
        }
        if (transaction.getExecutionDate() != null) {
            this.executionDate = transaction.getExecutionDate().toString();
        }else{
            this.executionDate = null;
        }

    }

    static TransactionData of(Transaction transaction) {
        return new TransactionData(transaction);
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
