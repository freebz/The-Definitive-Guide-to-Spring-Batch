// Listing 7-62. RepositoryItemReader

...
@Bean
@StepScope
public RepositoryItemReader<Customer> customerItemReader(CustomerRepository repository,
	     @Value("#{jobParameters['city']}") String city) {

    return new RepositoryItemReaderBuilder<Customer>()
	          .name("customerItemReader")
	          .arguments(Collections.singletonList(city))
	          .methodName("findByCity")
	          .repository(repository)
	          .sorts(Collections.singletonMap("lastName", Sort.Direction.ASC))
	          .build();
}
...
