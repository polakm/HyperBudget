package pl.com.michalpolak.hyperbudget.transaction.core.api;

import org.joda.time.YearMonth;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionStatistics;
import pl.com.michalpolak.hyperbudget.transaction.core.TransactionSummary;

import java.util.List;

public interface TransactionSummaryService {
    TransactionInfo getTransactionInfo(String id);

    List<TransactionInfo> getTransactionInfosList();

    TransactionSummary getTransactionsSummary();

    TransactionStatistics calculateTransactionStatistics();

    TransactionSummary getTransactionsSummaryPeMonth(YearMonth month);
}
