// Listing 7-52. Configuring the HibernateCursorItemReader

...
@Bean
@StepScope
public HibernateCursorItemReader<Customer> customerItemReader(
             EntityManagerFactory entityManagerFactory,
	     @Value("#{jobParameters['city']}") String city) {

    return new HibernateCursorItemReaderBuilder<Customer>()
	          .name("customerItemReader")
	          .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
	          .queryString("from Customer where city = :city")
	          .parameterValues(Collections.singletonMap("city", city))
	          .build();
}
...
