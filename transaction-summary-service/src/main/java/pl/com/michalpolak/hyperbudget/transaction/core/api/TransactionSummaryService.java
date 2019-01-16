package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.time.YearMonth;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;

public interface TransactionSummaryService {

    TransactionSummary getTransactionsSummaryPeMonth(YearMonth month);
}
