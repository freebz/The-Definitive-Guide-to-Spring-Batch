// Listing 7-56. Using the JpaQueryProvider

...
@Bean
@StepScope
public JpaPagingItemReader<Customer> customerItemReader (
             EntityManagerFactory entityManagerFactory,
	     @Value("#{jobParameters['city']}") String city) {

    CustomerByCityQueryProvider queryProvider =
	                 new CustomerByCityQueryProvider();
    queryProvider.setCityName(city);

    return new JpaPagingItemReaderBuilder<Customer>()
	          .name("customerItemReader")
	          .entityManagerFactory(entityManagerFactory)
	          .queryProvider(queryProvider)
	          .parameterValues(Collections.singletonMap("city", city))
	          .build();
}
...
