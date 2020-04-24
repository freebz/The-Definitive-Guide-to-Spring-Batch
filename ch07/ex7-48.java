// Listing 7-48. JdbcPagingItemReader Configuration

...
@Bean
@StepScope
public JdbcPagingItemReader<Customer> customerItemReader(DataSource dataSource,
	PagingQueryProvider queryProvider,
	@Value("#{jobParameters['city']}") String city) {

    Map<String, Object> parameterValues = new HashMap<>(1);
    parameterValues.put("city", city);

    return new JdbcPagingItemReaderBuilder<Customer>()
	    .name("customerItemReader")
	    .dataSource(dataSource)
	    .queryProvider(queryProvider)
	    .parameterValues(parameterValues)
	    .pageSize(10)
	    .rowMapper(new CustomerRowMapper())
	    .build();
}

@Bean
public SqlPagingQueryProviderFactoryBean pagingQueryProvider(DataSource dataSource) {
    SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();

    factoryBean.setDataSource(dataSource);
    factoryBean.setSelectClause("select *");
    factoryBean.setFromClause("from Customer");
    factoryBean.setWhereClause("where city = :city");
    factoryBean.setSortKey("lastName");

    return factoryBean;
}
