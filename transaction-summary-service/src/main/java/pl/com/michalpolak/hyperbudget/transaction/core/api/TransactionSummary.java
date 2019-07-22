package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.springframework.core.style.ToStringCreator;

import java.util.List;

public class TransactionSummary {

    private final List<TransactionInfo> transactionInfos;
    private final TransactionStatistics statistics;

    private TransactionSummary(List<TransactionInfo> transactionInfos, TransactionStatistics statistics) {
        this.transactionInfos = transactionInfos;
        this.statistics = statistics;
    }

    public static TransactionSummary of(List<TransactionInfo> transactionInfos, TransactionStatistics statistics) {
        return new TransactionSummary(transactionInfos, statistics);
    }

    public List<TransactionInfo> getTransactionInfos() {
        return transactionInfos;
    }

    public TransactionStatistics getStatistics() {
        return statistics;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("transactionInfos", transactionInfos)
                .append("statistics", statistics)
                .toString();
    }
}
