// Listing 7-28. copyFileStep

...
@Bean
public Step copyFileStep() {
    return this.stepBuilderFactory.get("copyFileStep")
	           .<Customer, Customer>chunk(10)
	           .reader(customerFileReader())
	           .writer(itemWriter())
	           .build();
}
...
