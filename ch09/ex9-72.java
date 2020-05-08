// Listing 9-72. ItemWriters and Step and Job Configuration

...
@Bean
@StepScope
public StaxEventItemWriter<Customer> delegateItemWriter() throws Exception {

    Map<String, Class> aliases = new HashMap<>();
    aliases.put("customer", Customer.class);

    XStreamMarshaller marshaller = new XStreamMarshaller();

    marshaller.setAliases(aliases);

    marshaller.afterPropertiesSet();

    return new StaxEventItemWriterBuilder<Customer>()
	            .name("customerItemWriter")
	            .marshaller(marshaller)
	            .rootTagName("customers")
	            .build();
}

@Bean
public MultiResourceItemWriter<Customer> multiCustomerFileWriter() throws Exception {

    return new MultiResourceItemWriterBuilder<Customer>()
	            .name("multiCustomerFileWriter")
	            .delegate(delegateItemWriter())
	            .itemCountLimitPerResource(25)
	            .resource(new FileSystemResource("Chapter9/target/customer"))
	            .build();
}

@Bean
public Step multiXmlGeneratorStep() throws Exception {
    return this.stepBuilderFactory.get("multiXmlGeneratorStep")
	            .<Customer, Customer>chunk(10)
	            .reader(customerJdbcCursorItemReader(null))
	            .writer(multiCustomerFileWriter())
	            .build();
}

@Bean
public Job xmlGeneratorJob() throws Exception {
    return this.jobBuilderFactory.get("xmlGeneratorJob")
	            .start(multiXmlGeneratorStep())
	            .build();
}
...
