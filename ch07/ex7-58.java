// Listing 7-58. StoredProcedureItemReader

...
@Bean
@StepScope
public StoredProcedureItemReader<Customer> customerItemReader(DataSource dataSource,
	     @Value("#{jobParameters['city']}") String city) {

    return new StoredProcedureItemReaderBuilder<Customer>()
	          .name("customerItemReader")
	          .dataSource(dataSource)
	          .procedureName("customer_list")
	          .parameters(new SqlParameger[]{
			  new SqlParameter("cityOption", Types.VARCHAR)})
	          .preparedStatementSetter(
		          new ArgumentPreparedStatementSetter(new Object[] {city}))
	          .rowMapper(new CustomerRowMapper())
	          .build();
}
...
