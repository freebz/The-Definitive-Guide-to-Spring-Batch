// Listing 7-54. customerItemReader with JPA

...
@Bean
@StepScope
public JpaPagingItemReader<Customer> customerItemReader (
             EntityManagerFactory entityManagerFactory,
	     @Value("#{jobParameters['city']}") String city) {

    return new JpaPagingItemReaderBuilder<Customer>()
	          .name("customerItemReader")
	          .entityManagerFactory(entityManagerFactory)
	          .queryString("select c from Customer c where c.city = :city")
	          .parameterValues(Collections.singletonMap("city", city))
	          .build()
}
...
