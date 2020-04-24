// Listing 7-19. Configuring the CustomerFileLineTokenizer

...
@Bean
@StepScope
public FlatFileItemReader<Customer> customerItemReader(@Value("#{jobParameters['customerFile']}") Resource inputFile) {
    return new FlatFileItemReaderBuilder<Customer>()
	    .name("customerItemReader")
	    .lineTokenizer(new CustomerFileLineTokenizer())
	    .fieldSetMapper(new CustomerFieldSetMapper())
	    .resource(inputFile)
	    .build();
}
...
