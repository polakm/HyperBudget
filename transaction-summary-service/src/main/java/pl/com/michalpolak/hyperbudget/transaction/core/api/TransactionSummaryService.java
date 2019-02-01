package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.time.YearMonth;

public interface TransactionSummaryService {

    TransactionSummary getTransactionsSummaryPeMonth(YearMonth month);
}
