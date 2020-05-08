// Listing 9-79. XML Configuration for CustomerXmlHeaderCallback

...
@Bean
public MultiResourceItemWriter<Customer> multiCustomerFileWriter(CustomerOutputFileSuffixCreator suffixCreator) throws Exception {

    return new MultiResourceItemWriterBuilder<Customer>()
	            .name("multiCustomerFileWriter")
	            .delegate(delegateItemWriter(null))
	            .itemCountLimitPerResource(25)
	            .resource(new FileSystemResource("Chapter9/target/customer"))
	            .resourceSuffixCreator(suffixCreator)
	            .build();
}
...
