// Listing 7-39. copyFileStep

...
@Bean
public Step copyFileStep() {
    return this.stepBuilderFactory.get("copyFileStep")
	           .<Customer, Customer>chunk(10)
	           .reader(customerFileReader(null))
	           .writer(itemWriter())
	           .build();
}
...
