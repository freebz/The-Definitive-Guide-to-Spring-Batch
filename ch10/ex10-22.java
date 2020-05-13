// Listing 10-22. applyTransactionsReader

...
@Bean
public JdbcCursorItemReader<Transaction> applyTransactionReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Transaction>()
	            .name("applyTransactionReader")
	            .dataSource(dataSource)
	            .sql("select transaction_id, " +
				    "account_account_id, " +
				    "description, " +
				    "credit, " +
				    "debit, " +
				    "timestamp " +
				    "from TRANSACTION " +
				    "order by timestamp")
	            .rowMapper((resultSet, i) ->
			            new Transaction(
					    resultSet.getLong("transaction_id"),
					    resultSet.getLong("account_account_id"),
					    resultSet.getString("description"),
					    resultSet.getBigDecimal("credit"),
					    resultSet.getBigDecimal("debit"),
					    resultSet.getTimestamp("teimstamp")))
	            .build();
}
...
