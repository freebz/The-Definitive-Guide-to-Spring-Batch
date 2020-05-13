// Listing 10-9. customerValidatingItemProcessor

...
@Bean
public ValidatingItemProcessor<CustomerUpdate> customerValidatimgItemProcessor(CustomerItemValidator validator) {

    ValidatingItemProcessor<CustomerUpdate> customerValidatimgItemProcessor =
	            new ValidatingItemProcessor<>(validator);

    customerValidatimgItemProcessor.setFilter(true);

    return customerValidatimgItemProcessor;
}
...
