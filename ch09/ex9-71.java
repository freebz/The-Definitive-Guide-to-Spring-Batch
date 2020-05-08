// Listing 9-71. The multiResource formatJob's ItemReader

...
@Bean
public JdbcCursorItemReader<Customer> customerJdbcCursorItemReader(DataSource dataSource) {

    return new JdbcCursorItemReaderBuilder<Customer>()
	            .name("customerItemReader")
	            .dataSource(dataSource)
	            .sql("select * from customer")
	            .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
	            .build();
}
...
