// Listing 11-11. fileTransactionReader

...
@Bean
@StepScope
public FlatFileItemReader<Transaction> fileTransactionReader(
                @Value("#{stepExecutionContext['file']}") Resource resource) {

    return new FlatFileItemREaderBuilder<Transaction>()
	            .name("flatFileTransactionReader")
	            .resource(resource)
	            .delimited()
	            .names(new String[] {"account", "amount", "timestamp"})
	            .fieldSetMapper(filedSet -> {
			    Transaction transaction = new Transaction();

			    transaction.setAccount(filedSet.readString("account"));
			    transaction.setAmount(filedSet.readBigDecimal("amount"));
			    transaction.setTimestamp(filedSet.readDate("timestamp", "yyyy-MM-dd HH:mm:ss"));

			    return transaction;
		    })
	            .build();
}
...
