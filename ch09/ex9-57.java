// Listing 9-57. formatJob Configured to Call the logCustomerAddress Method on CustomerService

...
@Bean
public PropertyExtractingDelegatingItemWriter<Customer> itemWriter(CustomerService customerService) {
    PropertyExtractingDelegatingItemWriter<Customer> itemWriter =
	            new PropertyExtractingDelegatingItemWriter<>();

    itemWriter.setTargetObject(customerService);
    itemWriter.setTargetMethod("logCustomerAddress");
    itemWriter.setFieldsUsedAsTargetMethodArguments(
	            new String[] {"address", "city", "state", "zip"});

    return itemWriter;
}

@Bean
public Step formatStep() throws Exception {
    return this.stepBuilderFactory.get("formatStep")
	            .<Customer, Customer>chunk(10)
	            .reader(customerFileReader(null))
	            .writer(itemWriter(null))
	            .build();
}

@Bean
public Job propertiesFormatJob() throws Exception {
    return this.jobBuilderFactory("propertiesFormatJob")
	            .start(formatStep())
	            .build();
}
...
