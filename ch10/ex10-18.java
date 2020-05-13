// Listing 10.18. importTransactions

...
@Bean
public Job job() throws Exception {
    return this.jobBuilderFactory.get("importJob")
	            .start(importCustomerUpdates())
	            .next(importTransactions())
	            .build();
}

@Bean
public Step importTransactions() {
    return this.stepBuilderFactory.get("importTransactions")
	            .<Transaction, Transaction>chunk(100)
	            .reader(transactionItemReader(null))
	            .writer(transactionItemWriter(null))
	            .build();
}
...
