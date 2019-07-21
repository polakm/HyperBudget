package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.springframework.core.style.ToStringCreator;

import java.util.List;

public class TransactionSummary {

   private List<TransactionInfo> transactionInfos;
    private TransactionStatistics statistics;


    public TransactionSummary(List<TransactionInfo> transactionInfos, TransactionStatistics statistics) {
        this.transactionInfos = transactionInfos;
        this.statistics =  statistics;
    }

    public List<TransactionInfo> getTransactionInfos() {
        return transactionInfos;
    }

    public void setTransactionInfos(List<TransactionInfo> transactionInfos) {
        this.transactionInfos = transactionInfos;
    }

    public TransactionStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(TransactionStatistics statistics) {
        this.statistics = statistics;
    }


    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("transactionInfos", transactionInfos)
                .append("statistics", statistics)
                .toString();
    }
}
