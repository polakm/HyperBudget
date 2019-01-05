package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;

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
}
