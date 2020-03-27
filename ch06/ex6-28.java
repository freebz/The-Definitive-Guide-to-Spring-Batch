// Listing 6-28. transactionJob Configured to Not Be Restartable

...
@Bean
public Job transactionJob() {
    return this.jobBuilderFactory.get("transactionJob")
	         .preventRestart()
	         .start(importTransactionFileStep())
	         .next(applyTransactionsStep())
	         .next(generateAccountSummaryStep())
	         .build();
}
...
