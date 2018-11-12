package pl.com.michalpolak.hyperbudget.transaction;

class BasicTransactionAggregationService implements  TransactionAggregationService {
    BasicTransactionAggregationService(TransactionServiceClient transactionServiceClient, CategoryServiceClient categoryServiceClient, AccountServiceClient accountServiceClient, TransactionAggregationRepository transactionAggregationRepository) {
    }
}
