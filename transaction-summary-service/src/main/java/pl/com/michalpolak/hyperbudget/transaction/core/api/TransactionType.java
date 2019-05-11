package pl.com.michalpolak.hyperbudget.transaction.core.api;

public enum TransactionType {

    INCOME("income"),
    EXPENSE("expense");

    private final String name;

    TransactionType(String name) {
       this.name =name;
    }

    @Override
    public String toString() {
        return name;
    }
}
