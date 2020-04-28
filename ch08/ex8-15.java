// Listing 8-15. ValidatingItemProcessor Configured to Filter Items

...
@Bean
public UniqueLastNameValidator validator() {
    UniqueLastNameValidator uniqueLastNameValidator = new UniqueLastNameValidator();

    uniqueLastNameValidator.setName("validator");

    return uniqueLastNameValidator;
}

@Bean
public ValidatingItemProcessor<Customer> customerValidatingItemProcessor() {
    ValidatingItemProcessor<Customer> itemProcessor =
	new ValidatingItemProcessor<>(validator());

    itemProcessor.setFilter(true);

    return itemProcessor;
}
...
