// Listing 10-4. Reading the Customer Update File

...
@Bean
@StepScope
public FlatFileItemReader<CustomerUpdate> customerUpdateItemReader(
@Value("#{jobParameters['customerUpdateFile']}") Resource inputFile) throws Exception {

    return new FlatFileItemReaderBulder<CustomerUpdate>()
	            .name("customerUpdateItemReader")
	            .resource(inputFile)
	            .lineTokenizer(customerUpdatesLineTokenizer())
	            .fieldSetMapper(customerupdateFieldSetMapper())
	            .build();
}
...
