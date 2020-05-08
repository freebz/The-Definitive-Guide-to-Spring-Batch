// Listing 9-84. Output, Step, and Job Configuration

...

@Bean
@StepScope
public StaxEventItemWriter<Customer> xmlDelegateItemWriter(
                @Value("#{jobParameters['outputFile']}") Resource outputFile) throws Execution {

    Map<String, Class> aliases = new HashMap<>();
    aliases.put("customer", Customer.class);

    XStreamMarshaller marshaller = new XStreamMarshaller();

    marshaller.setAliases(aliases);

    marshaller.afterPropertiesSet();

    return new StaxEventItemWriterBuilder<Customer>()
	            .name("customerItemWriter")
	            .resource(outputFile)
	            .marshaller(marshaller)
	            .rootTagName("customers")
	            .build();
}

@Bean
public JdbcBatchItemWriter<Customer> jdbcDelegateItemWriter(DataSource dataSource) {

    return new JdbcBatchItemWriterBuilder<Customer>()
	            .namedParametersJdbcTemplate(new NamedParameterJdbcTemplate(dataSource))
	            sql("INSERT INTO CUSTOMER (first_name, " +
			           "middle_initial, " +
				   "last_name, " +
				   "address, " +
				   "city, " +
				   "state, " +
				   "zip, " +
				   "email) " +
				   "VALUES(:firstName, " +
				   ":middleInitial, " +
				   ":lastName, " +
				   ":address, " +
				   ":city, " +
				   ":state, " +
				   ":zip, " +
				   ":email)")
	            .beanMapped()
	            .build();
}

@Bean
public CompositeItemWriter<Customer> compositeItemWriter() throws Exception {
    return new CompositeItemWriterBuilder<Customer>()
	            .delegates(Arrays.asList(xmlDelegateItemWriter(null),
				    jdbcDelegateItemWriter(null)))
	            .build();
}

@Bean
public Step compositeWriterStep() throws Exception {
    return this.stepBuilderFactory.get("compositeWriterStep")
	            .<Customer, Customer>chunk(10)
	            .reader(compositewriterItemReader(null))
	            .writer(compositeItemWriter())
	            .build();
}

@Bean
public Job compositeWriterJob() throws Exception {
    return this.jobBuilderFactory.get("compositeWriterJob")
	            .start(compositeWriterStep())
	            .build();
}
...
