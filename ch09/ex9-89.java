// Listing 9-89. Updated Configuration Registering the Appropriate ItemStream for Processing

...
@Bean
public Step classifierCompositeWriteStep() throws Exception {
    return this.stepBuilderFactory.get("classifierCompositeWriteStep")
	            .<Customer, Customer>chunk(10)
	            .reader(classifierCompositeWriteItemReader(null))
	            .writer(classifierCompositeItemWriter())
	            .stream(xmlDelegate(null))
	            .build();
}

@Bean
public Job classifierCompositeWriteJob() throws Exception {
    return this.jobBuilderFactory.get("classifierCompositeWriteJob")
	            .start(classifierCompositeWriteStep())
	            .build();
}

...
