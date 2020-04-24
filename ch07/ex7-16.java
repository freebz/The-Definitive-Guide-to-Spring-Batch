// Listing 7-16. customerFileReader Configured with the CustomerFieldSetMapper

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
	.fieldSetMapper(new CustomerFieldSetMapper())
	     .resource(inputFile)
	     .build();
}
...
