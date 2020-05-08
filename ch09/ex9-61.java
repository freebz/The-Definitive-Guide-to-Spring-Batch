// Listing 9-61. Input and Output of jmsFormatJob

...
@Bean
@StepScope
public FlatFileItemReader<Customer> customerFileReader(
                @Value("#{jobParameters['customerFile']}")Resource inputFile) {

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

@Bean
@StepScope
public StaxEventItemWriter<Customer> xmlOutputWriter(
                @Value("#{jobParameters['outputFile']}") Resource outputFile) {

    Map<String, Class> aliases = new HashMap<>();
    aliases.put("customer", Customer.class);

    XStreamMarshaller marshaller = new XStreamMarshaller();
    marshaller.setAliases(aliases);

    return new StaxEventItemWriterBuilder<Customer>()
	            .name("xmlOutputWriter")
	            .resource(outputFile)
	            .marshaller(marshaller)
	            .rootTagName("customers")
	            .build();
}
...
