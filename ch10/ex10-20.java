// Listing 10-20. transactionItemWriter

...
@Bean
public JdbcBatchItemWriter<Transaction> transactionItemWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Transaction>()
	            .dataSource(dataSource)
	            .sql("INSERT INTO TRANSACTION (TRANSACTION_ID, " +
			            "ACCOUNT_ACCOUNT_ID, " +
			            "DESCRIPTION, " +
			            "CREDIT, " +
			            "DEBIT, " +
			            "TIMESTAMP) VALUES (:transactionId, " +
			            ":accountId, " +
			            ":description, " +
			            ":credit, " +
			            ":debit, " +
			            ":timestamp)")
	            .beanMapped()
	            .build();
}
...
