// Listing 8-11. ItemProcessorAdapter Configuration

...
@Bean
public ItemProcessorAdapter<Customer, Customer> itemProcessor(UpperCaseNameService service)
{
    ItemProcessorAdapter<Customer, Customer> adapter = new ItemProcessorAdapter<>();

    adapter.setTargetObject(service);
    adapter.setTargetMethod("upperCase");

    return adapter;
}

@Bean
public Step copyFileStep() {

    return this.stepBuilderFactory.get("copyFileStep")
	        .<Customer, Customer>chunk(5)
	        .reader(customerItemReader(null))
	        .processor(itemProcessor(null))
	        .writer(itemWriter())
	        .build();
}
...
