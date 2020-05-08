// Listing 9-83. Reading in the customerWithEmail.csv File

...
@Bean
@StepScope
public FlatFileItemReader<Customer> compositewriterItemReader(
                @Value("#{jobParameters['customerFile']}")Resource inputFile) {

    return new FlatFileItemReaderBuilder<Customer>()
	            .name("compositewriterItemReader")
	            .resource(inputFile)
	            .delimited()
	            .names(new String[] {
			            "firstName",
				    "middleInitial",
				    "lastName",
				    "address",
				    "city",
				    "state",
				    "zip",
				    "email"})
	            .targetType(Customer.class)
	            .build();
}
...
