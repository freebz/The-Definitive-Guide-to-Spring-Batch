// Listing 9-17. Appending Data If the Output File Exists

...
@Bean
@StepScope
public FlatFileItemWriter<Customer> delimitedCustomerItemWriter(
                @Value("#{jobParameters['outputFile']}") Resource outputFile) {

    return new FlatFileItemWriterBuilder<Customer>()
	            .name("customerItemWriter")
	            .resource(outputFile)
	            .delimited()
	            .delimiter(";")
	            .names(new String[] {"zip",
			            "state",
			            "city",
			            "address",
			            "lastName",
			            "firstName"})
	            .append(true)
	            .build();
}
...
