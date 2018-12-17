package pl.com.michalpolak.hyperbudget.transaction.core.api;

import pl.com.michalpolak.hyperbudget.transaction.core.TransactionStatistics;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;

import java.util.List;

public interface TransactionAggregationService {
    AggregatedTransaction getAggregatedData(String id);

    List<AggregatedTransaction> getAggregatedTransactionsList();

    TransactionSummary getTransactionsSummary();

    TransactionStatistics calculateTransactionStatistics();
}
