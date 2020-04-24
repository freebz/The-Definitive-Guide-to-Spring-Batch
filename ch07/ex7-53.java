// Listing 7-53. Paging Database Access with Hibernate

...
@Bean
@StepScope
public HibernatePagingItemReader<Customer> customerItemReader (
      EntityManagerFactory entityManagerFactory,
        @Value("#{jobParameters['city']}") String city) {

    return new HibernatePagingItemReaderBuilder<Customer>()
	    .name("customerItemReader")
	    .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
	    .queryString("from Customer where city = :city")
	    .parameterValues(Collections.singletonMap("city", city))
	    .pageSize(10)
	    .build();
}
...
