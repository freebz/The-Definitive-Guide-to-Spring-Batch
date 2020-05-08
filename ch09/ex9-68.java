// Listing 9-68. ItemReader and Itemwriter for Step 1

...
@Bean
@StepScope
public FlatFileItemReader<Customer> customerEmailFileReader(
                @Value("#{jobParameters['customerFile']}")Resource inputFile) {

    return new FlatFileItemReaderBuilder<Customer>()
	            .name("customerFileReader")
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

@Bean
public JdbcBatchItemWriter<Customer> customerBatchWriter(DataSource dataSource) {

    return new JdbcBatchItemWriterBuilder<Customer>()
	            .namedParametersJdbcTemplate(new NamedParameterJdbcTemplate(dataSource))
	            .sql("INSERT INTO CUSTOMER (first_name, middle_initial, last_name, address, city, state, zip, email) " +
			            "VALUES(:firstName, :middleInitial, :lastName, :address, :city, :state, :zip, :email)")
	            .beanMapped()
	            .build();
}
...
