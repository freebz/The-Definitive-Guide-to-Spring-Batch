// Listing 9-5. Configuring the Format Job's Input

...
@Bean
@StepScope
public FlatFileItemReader<Customer> customerFileReader(
        @Value("#{jobParameters['customerFile']}")Resource inputfiel) {

    return new FlatFileItemReaderBuilder<Customer>()
	    .name("customerFileReader")
	    .resource(inputFile)
	    .delimited()
	    .names(new String[] {"firstName",
		    "middleInitial",
		    "lastName",
		    "address",
		    "city",
		    "state",
		    "zip"})
	    .targetType(Customer.class)
	    .build();
}
...
