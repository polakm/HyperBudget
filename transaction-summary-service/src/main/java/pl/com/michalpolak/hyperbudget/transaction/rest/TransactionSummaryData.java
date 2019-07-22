package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.google.gson.Gson;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

class TransactionSummaryData extends ResourceSupport {

    private final List<TransactionInfoData> transactions;
    private final RangeData range;
    private final StatisticsData statistics;

    private TransactionSummaryData(List<TransactionInfoData> transactions, RangeData range, StatisticsData statistics) {
        this.transactions = transactions;
        this.range = range;
        this.statistics = statistics;
    }

    static TransactionSummaryData of(List<TransactionInfoData> transactions, RangeData range, StatisticsData statistics) {
        return new TransactionSummaryData(transactions, range, statistics);
    }

    List<TransactionInfoData> getTransactions() {
        return transactions;
    }

    StatisticsData getStatistics() {
        return statistics;
    }

    RangeData getRange() {
        return range;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
