// Listing 6-32. Configuring a Step to Be Re-executed if Complete

...
@Bean
public Step importTransactionFileStep() {
    return this.stepBuilderFactory.get("importTransactionFileStep")
	         .allowStartIfComplete(true)
	         .<Transaction, Transaction>chunk(100)
	         .reader(transactionReader())
	         .writer(transactionWriter(null))
	         .allowStartIfComplete(true)
	         .listener(transactionReader())
	         .build();
}
...
