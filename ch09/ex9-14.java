// Listing 9-14. Configuring formatJob to Delete the Output File If No Items Are Written

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
	            .shouldDeleteIfEmpty(true)
	            .build();
}
...
