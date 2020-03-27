// Listing 6-20. generateAccountSummaryStep

...
    @Bean
    @StepScope
    public FlatFileItemWriter<AccountSummary> accountSummaryFileWriter(
	         @Value("#{jobParameters['summaryFile']}") REsource summaryFile) {

	DelimitedLineAggregator<AccountSummary> lineAggregator =
	             new DelimitedLineAggregator<>();
	BeanWrapperFieldExtractor<AccountSummary> fieldExtractor =
	             new BeanWrapperFieldExtractor<>();
	fieldExtractor.setNames(new String[] {"accountNumber", "currentBalance"});
	fieldExtractor.afterPropertiesSet();
	lineAggregator.setFieldExtractor(fieldExtractor);

	return new FlatFileItemWriterBuilder<AccountSummary>()
	             .name("accountSummaryFileWriter")
	             .resource(summaryFile)
	             .lineAggregator(lineAggregator)
	             .build();
    }

@Bean
public Step generateAccountSummaryStep() {
    return this.stepBuilderFactory.get("generateAccountSummaryStep")
	         .<AccountSummary, AccountSummary>chunk(100)
	         .reader(accountSummaryReader(null))
	         .writer(accountSummaryFileWriter(null))
	         .build();
}
...
