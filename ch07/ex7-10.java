// Listing 7-10. customerFileReader with the DelemitedLineTokenizer

...
@Bean
@StepScope
public FlatFileItemReader<Customer> customerItemReader(@Value("#{jobParameters['customerFile']}") Resource inputFile) {
    return new FlatFileItemReaderBuilder<Customer>()
	     .name("customerItemReader")
	     .delimited()
	     .names(new String[] {"firstName",
		     "middleInitial",
		     "lastName",
		     "addressNumber",
		     "street",
		     "city",
		     "state",
		     "zipcode"})
	     .targetType(Customer.class)
	     .resource(inputFile)
	     .build();
}
...
