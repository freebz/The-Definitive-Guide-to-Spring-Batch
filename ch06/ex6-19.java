// Listing 6-19. applyTransactionStep

...
@Bean
@StepScope
public JdbcCursorItemReader<AccountSummary> accountSummaryReader(DataSource dateSource) {
    return new JdbcCursorItemReaderBuilder<AccountSummary>()
	         .name("accountSummaryReader")
	         .dataSource(dataSource)
	         .sql("SELECT ACCOUNT_NUMBER, CURRENT_BALANCE " +
	                     "FROM ACCOUNT_SUMMARY A " +
		             "WHERE A.ID IN (" +
	                     "      SELECT DISTINCT T.ACCOUNT_SUMMARY_ID " +
	                     "ORDER BY A.ACCOUNT_NUMBER")
	         .rowMapper((resultSet, rowNumber) -> {
		     AccountSummary summary = new AccountSummary();

		     summary.setAccountNumber(resultSet.getString("account_number"));
		     summary.setCurrentBalance(resultSet.getDouble("current_balance"));

		     return summary;
		 }).build();
    }

@Bean
public TransactionDao transactionDao(DataSource dataSource) {
    return new TransactionDaoSupport(dataSource);
}

@Bean
public TransactionApplierProcessor transactionApplierProcessor() {
    return new TransactionApplierProcessor(transactionDao(null));
}

@Bean
public JdbcBatchItemWriter<AccountSummary> accountSummaryWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<AccountSummary>()
	         .dataSource(dataSource)
	         .itemSqlParameterSourceProvider(
		             new BeanPropertyItemSqlParameterSourceProvider<>())
	         .sql("UPDATE ACCOUNT_SUMMARY " +
		             "SET CURRENT_BALANCE = :currentBalance " +
		             "WHERE ACCOUNT_NUMBER = :accountNumber")
	         .build();
}

@Bean
public Step applyTransactionsStep() {
    return this.stepBuilderFactory.get("applyTransactionStep")
	         .<AccountSummary, AccountSummary>chunk(100)
	         .reader(accountSummaryReader(null))
	         .processor(transactionApplierProcessor())
	         .writer(accountSummaryWriter(null))
	         .build();
}
...
