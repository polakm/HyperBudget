package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.springframework.hateoas.ResourceSupport;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;

import java.util.ArrayList;
import java.util.List;

class TransactionSummaryData extends ResourceSupport {

    private List<TransactionInfoData> transactions;
    private RangeData range;
    private StatisticsData statistics;


    TransactionSummaryData(TransactionSummary summary, RangeData range) {
        this.transactions = new ArrayList<>();
        summary.getTransactionInfos().forEach(t -> this.transactions.add(new TransactionInfoData(t)));
        this.statistics = new StatisticsData(summary.getStatistics());
        this.range = range;
    }

    public List<TransactionInfoData> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionInfoData> transactions) {
        this.transactions = transactions;
    }

    public StatisticsData getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsData statistics) {
        this.statistics = statistics;
    }

    public RangeData getRange() {
        return range;
    }

    public void setRange(RangeData range) {
        this.range = range;
    }
}
