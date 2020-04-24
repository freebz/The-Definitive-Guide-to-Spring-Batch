// Listing 7-46. Processing Only Customers by a Given City

...
@Bean
public JdbcCursorItemReader<Customer> customerItemReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Customer>()
	     .name("customerItemReader")
	     .dataSource(dataSource)
	     .sql("select * from customer where city = ?")
	     .rowMapper(new CustomerRowMapper())
	     .preparedStatementSetter(citySetter(null))
	     .build();
}

@Bean
@StepScope
public ArgumentPreparedStatementSetter citySetter(
      @Value("#{jobParameters['city']}") String city) {

    return new ArgumentPreparedStatementSetter(new Object [] {city});
}
...
