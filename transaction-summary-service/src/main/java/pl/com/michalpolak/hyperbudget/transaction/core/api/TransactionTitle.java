package pl.com.michalpolak.hyperbudget.transaction.core.api;

public class TransactionTitle {

    private final String value;

    private TransactionTitle(String value) {
        this.value = value;
    }

    public static TransactionTitle fromString(String value) {
        return new TransactionTitle(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TransactionTitle) {
            return value.equals(((TransactionTitle) obj).value);
        }
        return false;
    }
}
