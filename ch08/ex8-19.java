// Listing 8-19. CompositeItemProcessor Configuration

...
@Bean
public CompositeItemProcessor<Customer, Customer> itemProcessor() {
    CompositeItemProcessor<Customer, Customer> itemProcessor =
	         new CompositeItemProcessor<>();

    itemProcessor.setDelegates(Arrays.asList(
		 customerValidatingItemProcessor(),
		 upperCaseItemProcessor(null),
		 lowerCaseItemProcessor(null)));

    return itemProcessor;
}
...
