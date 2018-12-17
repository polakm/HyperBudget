package pl.com.michalpolak.hyperbudget.transaction.rest;

import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;

import java.util.ArrayList;
import java.util.List;

public class TransactionSummaryData {

    private List<AggregatedTransactionData> transactions;
    private StatisticsData statistics;



    public TransactionSummaryData(TransactionSummary summary) {
        this.transactions = new ArrayList<>();
        summary.getAggregatedTransactions().forEach(t -> this.transactions.add(new AggregatedTransactionData(t)));
        statistics = new StatisticsData(summary.getStatistics());
    }
    public List<AggregatedTransactionData> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<AggregatedTransactionData> transactions) {
        this.transactions = transactions;
    }

    public StatisticsData getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsData statistics) {
        this.statistics = statistics;
    }


}
