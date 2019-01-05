package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.springframework.hateoas.ResourceSupport;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;

import java.util.ArrayList;
import java.util.List;

public class TransactionSummaryData extends ResourceSupport {

    private List<TransactionInfoData> transactions;
    private StatisticsData statistics;



    public TransactionSummaryData(TransactionSummary summary) {
        this.transactions = new ArrayList<>();
        summary.getTransactionInfos().forEach(t -> this.transactions.add(new TransactionInfoData(t)));
        statistics = new StatisticsData(summary.getStatistics());
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


}
