// Listing 8-9. UniqueLastNameValidator Configuration

...
@Bean
public UniqueLastNameValidator validator() {
    UniqueLastNameValidator uniqueLastNameValidator = new UniqueLastNameValidator();

    uniqueLastNameValidator.setName("validator");

    return uniqueLastNameValidator;
}

@Bean
public ValidatingItemProcessor<Customer> customerValidatingItemProcessor() {
    return new ValidatingItemProcessor<>(validator());
}

@Bean
public Step copyFileStep() {

    return this.stepBuilderFactory.get("copyFileStep")
	         .<Customer, Customer>chunk(5)
	         .reader(customerItemReader(null))
	         .processor(customerValidatingItemProcessor())
	         .writer(itemWriter())
	         .stream(validator())
	         .build();
}
...
