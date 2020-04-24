// Listing 7-75. Configuring the CustomerItemListener

...
@Bean
public CustomerItemListener customerListener() {
    return new CustomerItemListener();
}

@Bean
public Step copyFileStep() {
    return this.stepBuilderFactory.get("copyFileStep")
	        .<Customer, Customer>chunk(10)
	        .reader(customerFileReader(null))
	        .writer(outputWriter(null))
	        .faultTolerant()
	        .skipLimit(100)
	        .skip(Exception.class)
	        .listener(customerListener())
	        .build();
}
...
