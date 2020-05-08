// Listing 9-27. formatJob Configured for JDBC Database Writing

...
@Configuration
public class JdbcFormatJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public JdbcFormatJob(JobBuilderFactory jobBuilderFactory,
		    StepBuilderFactory stepBuilderFactory) {

	this.jobBuilderFactory = jobBuilderFactory;
	this.stepBuilderFactory = stepBuilderFactory;
    }

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
    public JdbcBatchItemWriter<Customer> jdbcCustomerWriter(DataSource dataSource) throws Exception {
	return new JdbcBatchItemWriterBuilder<Customer>()
	                .dataSource(dataSource)
	                .sql("INSERT INTO CUSTOMER (first_name, " +
			                "middle_initial, " +
			                "address, " +
			                "city, " +
			                "state, " +
			                "zip) VALUES (?, ?, ?, ?, ?, ?, ?)")
	                .itemPreparedStatementSetter(
			        new CustomerItemPreparedStatementSetter())
	                .build();
    }

    @Bean
    public Step xmlFormatStep() throws Exception {
	return this.stepBuilderFactory.get("xmlFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(jdbcCustomerWriter(null))
	                .build();
    }

    @Bean
    public Job xmlFormatJob() throws Exception {
	return this.jobBuilderFactory.get("xmlFormatJob")
	                .start(xmlFormatStep())
	                .build();
    }
}
