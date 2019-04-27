package pl.com.michalpolak.hyperbudget.transaction.event;

class EventContext {

    public static Builder builder() {
       return new Builder();
    }

    static class Builder {

        private String id;
        private String title;
        private String executionDate;
        private String amount;
        private String currencyCode;
        private String accountId;
        private String categoryId;
        private String type;

        Builder withId(String id) {
            this.id = id;
            return this;
        }

        Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        Builder withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        Builder withExecutionDate(String executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        Builder withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        Builder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        Builder withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        Builder withType(String type) {
            this.type = type;
            return this;
        }

        EventContext build() {
            return new EventContext(id, title, executionDate, amount, currencyCode, accountId, categoryId, type);
        }
    }

    private final String id;
    private final String title;
    private final String executionDate;
    private final String amount;
    private final String currencyCode;
    private final String accountId;
    private final String categoryId;
    private final String type;

    private EventContext(String id, String title, String executionDate, String amount, String currencyCode, String accountId, String categoryId, String type) {
        this.id = id;
        this.title = title;
        this.executionDate = executionDate;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getType() {
        return type;
    }

}
