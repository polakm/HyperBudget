package pl.com.michalpolak.hyperbudget.transaction.core.api;

import java.util.List;

public interface TransactionAggregationService {
    AggregatedTransaction getAggregatedData(String id);

    List<AggregatedTransaction> getAggregatedTransactionsList();
}
