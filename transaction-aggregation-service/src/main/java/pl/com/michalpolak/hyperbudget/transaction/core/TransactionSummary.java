package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.AggregatedTransaction;

import java.util.List;

public class TransactionSummary {

   private List<AggregatedTransaction> aggregatedTransactions;
    private TransactionStatistics statistics;


    public TransactionSummary(List<AggregatedTransaction> aggregatedTransactions, TransactionStatistics statistics) {
        this.aggregatedTransactions = aggregatedTransactions;
        this.statistics =  statistics;
    }

    public List<AggregatedTransaction> getAggregatedTransactions() {
        return aggregatedTransactions;
    }

    public void setAggregatedTransactions(List<AggregatedTransaction> aggregatedTransactions) {
        this.aggregatedTransactions = aggregatedTransactions;
    }

    public TransactionStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(TransactionStatistics statistics) {
        this.statistics = statistics;
    }
}
