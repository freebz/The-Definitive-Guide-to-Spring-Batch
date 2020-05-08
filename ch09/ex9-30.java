// Listing 9-30. jdbcBatchWriter Using BeanPropertyItemWriter(DataSource dataSource)

...
@Bean
public JdbcBatchItemWriter<Customer> jdbcCustomerWriter(DataSource dataSource)
    throws Exception {
    return new JdbcBatchItemWriterBuilder<Customer>()
	            .dataSource(dataSource)
	            .sql("INSERT INTO CUSTOMER (first_name, " +
			            "middle_initial, " +
			            "last_name, " +
			            "address, " +
			            "city, " +
			            "state, " +
			            "zip) VALUES (:firstName, " +
			            ":middleInitial, " +
			            ":lastName, " +
			            ":address, " +
			            ":city, " +
			            ":state, " +
			            ":zip)")
	            .beanMapped()
	            .build();
}
...
