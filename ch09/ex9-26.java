// Listing 9-26. jdbcBatchWriter's Configuration

...
@Bean
@StepScope
public JdbcBatchItemWriter<Customer> jdbcCustomerWriter(DataSource dataSource) throws Exception {
    return new JdbcBatchItemWriterBuilder<Customer>()
	            .dataSource(dataSource)
	            .sql("INSERT INTO CUSTOMER (first_name, " +
			            "middle_initial, " +
			            "last_name, " +
			            "address, " +
			            "city, " +
			            "state, " +
			            "zip) VALUES (?, ?, ?, ?, ?, ?, ?)")
	            .itemPreparedStatementSetter(new CustomerItemPreparedStatementSetter())
	            .build();
}
...
