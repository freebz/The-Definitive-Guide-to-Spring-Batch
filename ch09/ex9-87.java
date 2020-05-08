// Listing 9-87. Configuration of the ClassifierCompositeItemWriter and Dependencies

...

@Bean
public ClassifierCompositeItemWriter<Customer> classifierCompositeItemWriter() throws Exception {
    Classifier<Customer, ItemWriter<? super Customer>> classifier = new CustomerClassifier(xmlDelegate(null), jdbcItemWriter(null));

    return new ClassifierCompositeItemWriterBuilder<Customer>()
	            .classifier(classifier)
	            .build();
}

@Bean
public Step classifierCompositeWriterStep() throws Exception {
    return this.stepBuilderFactory.get("classifierCompositeWriterStep")
	            .<Customer, Customer>chunk(10)
	            .reader(classifierCompositeWriterItemReader(null))
	            .writer(classifierCompositeItemWriter())
	            .build();
}

@Bean
public Job classifierCompositeWriterJob() throws Exception {
    return this.jobBuilderFactory.get("classifierCompositeWriterJob")
	            .start(classifierCompositeWriterStep())
	            .build();
}
...
