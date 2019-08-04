package pl.com.michalpolak.hyperbudget.transaction.rest;

import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.YearMonth;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionInfo;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionStatistics;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionSummary;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class TransactionSummaryDataMapper {

    TransactionSummaryData mapToData(TransactionSummary summary, YearMonth yearMonth) {

        List<TransactionInfoData> transactions = summary.getTransactionInfos().stream().map(this::mapToTransactionInfoData).collect(Collectors.toList());
        RangeData range = mapToRangeData(yearMonth);
        StatisticsData statistics = mapStatisticsData(summary.getStatistics());
        return TransactionSummaryData.of(transactions, range, statistics);
    }

    TransactionInfoData mapToTransactionInfoData(TransactionInfo transactionInfo) {

        TransactionInfoData.Builder builder = TransactionInfoData.builder();

        Optional.ofNullable(transactionInfo.getId()).ifPresent(transactionId -> {
            builder.withId(transactionId.toString());

        });
        Optional.ofNullable(transactionInfo.getTitle()).ifPresent(transactionTitle -> {
            builder.withTitle(transactionTitle.toString());

        });
        Optional.ofNullable(transactionInfo.getAccount()).filter(account -> !account.getId().isEmpty()).ifPresent(account -> {
            builder.forAccount(account.getId());
            builder.withAccountName(account.getName());
        });

        Optional.ofNullable(transactionInfo.getCategory()).filter(category -> !category.getId().isEmpty()).ifPresent(category -> {
            builder.inCategory(category.getId());
            builder.withCategoryName(category.getName());
        });

        Optional.ofNullable(transactionInfo.getAmount()).ifPresent(ammount -> {
            builder.withAmount(ammount.getAmount().toPlainString());
            builder.withCurrencyCode(ammount.getCurrencyUnit().getCode());
        });

        Optional.ofNullable(transactionInfo.getAmount()).filter(Money::isPositive).ifPresent(ammount -> {
            builder.withType(TransactionType.INCOME);
        });

        Optional.ofNullable(transactionInfo.getAmount()).filter(Money::isNegative).ifPresent(ammount -> {
            builder.withType(TransactionType.EXPENSE);
        });

        Optional.ofNullable(transactionInfo.getExecutionDate()).
                map(d -> d.toDateTime(DateTimeZone.UTC)).
                map(DateTime::toString).
                ifPresent(builder::onExecutionDate);

        return builder.build();
    }

    final StatisticsData mapStatisticsData(TransactionStatistics statistics) {

        StatisticsData.Builder builder = StatisticsData.builder();
        builder.withBalance(statistics.totalSum().toString());
        builder.withIncome(statistics.sumOfIncomes().toString());
        builder.withExpense(statistics.sumOfExpenses().toString());
        builder.withBalanceAbs(statistics.totalSum().abs().toString());
        builder.withIncomeAbs(statistics.sumOfIncomes().abs().toString());
        builder.withExpenseAbs(statistics.sumOfExpenses().abs().toString());
        return builder.build();
    }

    final RangeData mapToRangeData(YearMonth yearMonth) {

        return RangeData.of(yearMonth.getYear(), yearMonth.getMonthOfYear(), yearMonth.monthOfYear().getAsText());
    }
}
