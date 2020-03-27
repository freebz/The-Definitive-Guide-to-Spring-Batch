// Listing 6-25. Reconfigured transactionJob

...
@Bean
public Job transactionJob() {
    return this.jobBuilderFactory.get("transactionJob")
	         .start(importTransactionFileStep())
	         .next(applyTransactionStep())
	         .next(generateAccountSummaryStep())
	         .build();
}
...
