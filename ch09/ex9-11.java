// Listing 9-11. flatFileOutputWriter Configuration

...
@Bean
@StepScope
public FlatFileItemWriter<Customer> customerItemWriter(
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
	            .build();
}
...
