// Listing 7-78. Configuring the EmptyInputStepFailer

...
@Bean
public EmptyInputStepFailer emptyFileFailer() {
    return new EmptyInputStepFailer();
}

@Bean
public Step copyFileStep() {
    return this.stepBuilderFactory.get("copyFileStep")
	        .<Customer, Customer>chunk(10)
	        .reader(customerFileReader(null))
	        .writer(outputWriter(null))
	        .listener(emptyFileFailer())
	        .build();
}
...
