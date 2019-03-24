package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.springframework.hateoas.ResourceSupport;
import java.util.List;

class TransactionSummaryData extends ResourceSupport {

    private List<TransactionInfoData> transactions;
    private RangeData range;
    private StatisticsData statistics;

    public TransactionSummaryData(){

    };

    public TransactionSummaryData(List<TransactionInfoData> transactions, RangeData range, StatisticsData statistics) {
        this.transactions = transactions;
        this.range = range;
        this.statistics = statistics;
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
