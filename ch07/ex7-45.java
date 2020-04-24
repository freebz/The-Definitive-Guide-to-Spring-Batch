// Listing 7-45. JDBC Cursor-Based customerItemReader

...
@Bean
public JdbcCursorItemReader<Customer> customerItemReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Customer>()
	    .name("customerItemReader")
	    .dataSource(dataSource)
	    .sql("select * from customer")
	    .rowMapper(new CustomerRowMapper())
	    .build();
}
...
