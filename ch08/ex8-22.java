// Listing 8-22. ClassifierCompositeItemProcessor Configuration

...
@Bean
public Classifier classifier() {
    return new ZipCodeClassifier(upperCaseItemProcessor(null),
		 lowerCaseItemProcessor(null));
}

@Bean
public ClassifierCompositeItemProcessor<Customer, Customer> itemProcessor() {
    ClassifierCompositeItemProcessor<Customer, Customer> itemProcessor =
	new ClassifierCompositeItemProcessor<>();

    itemProcessor.setClassifier(classifier());

    return itemProcessor;
}
...
