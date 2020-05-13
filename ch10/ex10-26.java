// Listing 10-26. generateStatements Step

...
@Bean
public Job job() throws Exception {
    return this.jobBuilderFactory.get("importJob")
	            .start(importCustomerUpdates())
	            .next(importTransactions())
	            .next(applyTransactions())
	            .next(generateStatements(null))
	            .build();
}
...
@Bean
public Step generateStatements(AccountItemProcessor itemProcessor) {
    return this.stepBuilderFactory.get("generateStatements")
	            .<Statement, Statement>chunk(1)
	            .reader(statementItemReader(null))
	            .processor(itemProcessor)
	            .writer(statementItemWriter(null))
	            .build();
}
...
