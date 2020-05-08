// Listing 9-15. Configuring formatJob to Not Delete the Output File If It Already Exists

...
@Bean
@StepScope
public FlatFileItemWriter<Customer> delemitedCustomerItemWriter(
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
	            .shouldDeleteIfExists(false)
	            .build();
}
...
