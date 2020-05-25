// Listing 13-11. ImportJobConfiguration#customerUpdateItemReader

...
@Bean
@StepScope
public FlatFileItemReader<CustomerUpdate> customerUpdateItemReader(
        @Value("#{jobParameters['customerUpdateFile']}") Resource inputFile)
        throws Exception {

    return new FlatFileItemReaderBuilder<CustomerUpdate>()
	            .name("customerUpdateItemReader")
	            .resource(inputFile)
	            .lineTokenizer(customerUpdatesLineTokenizer())
	            .fieldSetMapper(customerUpdateFieldSetMapper())
	            .build();
}
...
