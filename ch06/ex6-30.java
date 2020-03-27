// Listing 6-30. Allowing the File Import to Be Attempted Only Twice

...
@Bean
public Step importTransactionFileStep() {
    return this.stepBuilderFactory.get("importTransactionFileStep")
	         .startLimit(2)
	         .<Transaction, Transaction>chunk(100)
	         .reader(transactionReader())
	         .writer(transactionWriter(null))
	         .allowStartIfComplete(true)
	         .listener(transactionReader())
	         .build();
}
...
