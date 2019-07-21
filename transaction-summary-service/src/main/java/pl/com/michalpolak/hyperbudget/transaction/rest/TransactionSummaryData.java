package pl.com.michalpolak.hyperbudget.transaction.rest;

import com.google.gson.Gson;
import org.springframework.hateoas.ResourceSupport;
import java.util.List;

class TransactionSummaryData extends ResourceSupport {

    private List<TransactionInfoData> transactions;
    private RangeData range;
    private StatisticsData statistics;

    TransactionSummaryData(){

    };

    TransactionSummaryData(List<TransactionInfoData> transactions, RangeData range, StatisticsData statistics) {
        this.transactions = transactions;
        this.range = range;
        this.statistics = statistics;
    }

    List<TransactionInfoData> getTransactions() {
        return transactions;
    }

    void setTransactions(List<TransactionInfoData> transactions) {
        this.transactions = transactions;
    }

    StatisticsData getStatistics() {
        return statistics;
    }

    void setStatistics(StatisticsData statistics) {
        this.statistics = statistics;
    }

    RangeData getRange() {
        return range;
    }

    void setRange(RangeData range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
