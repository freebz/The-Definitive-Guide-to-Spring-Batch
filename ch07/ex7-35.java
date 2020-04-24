// Listing 7-35. customerFileReader Configured with the StaxEventItemReader

...
@Bean
@StepScope
public StaxEventItemReader<Customer> customerFileReader(
    @Value("#{jobParameters['customerFile']}") Resource inputFile) {

    return new StanxEventItemReaderBuilder<Customer>()
	           .name("customerFileReader")
	           .resource(inputFile)
	           .addFragmentRootElements("customer")
	           .unmarshaller(customerMarshaller())
	           .build();
}
...
