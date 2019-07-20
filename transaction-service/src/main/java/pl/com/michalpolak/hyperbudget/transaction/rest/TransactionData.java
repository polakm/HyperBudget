package pl.com.michalpolak.hyperbudget.transaction.rest;

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

    TransactionData(String id, String title, String executionDate, String amount, String currencyCode, String accountId, String categoryId, String type) {
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

    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }
}
