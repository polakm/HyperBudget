package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import org.joda.time.YearMonth;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;

import java.util.List;

public interface TransactionRepository {

   List<TransactionInfo> getTransactionInfosByMonth(YearMonth month);
}
