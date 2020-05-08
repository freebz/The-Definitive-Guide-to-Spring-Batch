// Listing 9-6. Output Configuration for Format Job

...
@Bean
@StepScope
public FlatFileItemWriter<Customer> customerItemWriter(
    @Value("#{jobParameters['outputFile']}") Resource outputFile) {

    return new FlatFileItemWriterBuilder<Customer>()
	            .name("customerItemWriter")
	            .resource(outputFile)
	            .formatted()
	            .format("%s %s lives at %s %s in %s, %s.")
	            .names(new String[] {"firstName",
			    "lastName",
			    "address",
			    "city",
			    "state",
			    "zip"})
	            .build();
}
...
