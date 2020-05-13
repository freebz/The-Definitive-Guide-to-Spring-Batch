// Listing 10-21. applyTransactions Step

...
@Bean
public Job job() throws Exception {
    return this.jobBuilderFactory.get("importJob")
	            .start(importCustomerUpdates())
	            .next(importTransactions())
	            .next(applyTransactions())
	            .build();
}
...

@Bean
public Step applyTransactions() {
    return this.stepBuilderFactory.get("applyTransactions")
	            .<Transaction, Transaction>chunk(100)
	            .reader(applyTransactionReader(null))
	            .writer(applyTransactionWriter(null))
	            .build();
}
...
