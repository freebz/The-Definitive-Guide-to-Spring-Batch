// Listing 7-6. The copyFileStep and copyFileJob

...
@Bean
public Step copyFileStep() {
    return this.stepBuilderFactory.get("copyFileStep")
	            .<Customer, Customer>chunk(10)
	            .reader(customerItemReader(null))
	            .writer(outputWriter(null))
	            .build();
}

@Bean
public Job job() {
    return this.jobBuilderFactory.get("job")
	        .start(copyFileStep())
	        .build();
}
...
