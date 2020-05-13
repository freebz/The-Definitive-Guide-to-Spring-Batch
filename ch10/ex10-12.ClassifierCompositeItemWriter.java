// Listing 10-12. customerUpdateItemWriter

...
@Bean
public ClassifierCompositeItemWriter<CustomerUpdate> customerUpdateItemWriter() {

    CustomerUpdateClassifier classifier =
	            new CustomerUpdateClassifier(customerNameUpdateItemWriter(null),
				    customerAddressUpdateItemWriter(null),
				    customerContactUpdateItemWriter(null));

    ClassifierCompositeItemWriter<CustomerUpdate> compositeItemWriter =
	            new ClassifierCompositeItemWriter<>();

    compositeItemWriter.setClassifier(classifier);

    return compositeItemWriter;
}
...
