// Listing 10-23. applyTransactionsWriter

...
@Bean
public JdbcBatchItemWriter<Transaction> applyTransactionWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Transaction>()
	            .dataSource(dataSource)
	            .sql("UPDATE ACCOUNT SET " +
			            "BALANCE = BALANCE + :transactionAmount " +
				    "WHERE ACCOUNT_ID = :accountId")
	            .beanMapped()
	            .assertUpdates(false)
	            .build();
}
...
