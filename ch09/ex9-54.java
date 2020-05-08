// Listing 9-54. ItemWriterAdapter Configuration

...
@Bean
public ItemWriterAdapter<Customer> itemWriter(CustomerService customerService) {
    ItemWriterAdapter<Customer> customerItemWriterAdapter = new ItemWriterAdapter<>();

    customerItemWriterAdapter.setTargetObject(customerService);
    customerItemWriterAdapter.setTargetMethod("logCustomer");

    return customerItemWriterAdapter;
}

@Bean
public Step formatStep() throws Exception {
    return this.stepBuilderFactory.get("jpaFormatStep")
	            .<Customer, Customer>chunk(10)
	            .reader(customerFileReader(null))
	            .writer(itemWriter(null))
	            .build();
}

@Bean
public Job itemWriterAdapterFormatJob() throws Exception {
    return this.jobBuilderFactory.get("itemWriterAdapterFromatJob")
	            .start(formatStep())
	            .build();
}
...
