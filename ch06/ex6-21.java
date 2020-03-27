// Listing 6-21. transactionJob

...
@Bean
public Job transactionJob() {
    return this.jobBuilderFactory.get("transactionJob")
	         .start(importTransactionFileStep())
	         .on("STOPPED").stopAndRestart(importTransactionFileStep())
	         .from(importTransactionFileStep()).on("*").to(applyTransactionsStep())
	         .from(applyTransactionsStep()).next(generateAccountSummaryStep())
	         .end()
	         .build();
}
...
