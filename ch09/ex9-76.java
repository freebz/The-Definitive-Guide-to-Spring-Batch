// Listing 9-76. Configuring CustomerOutputFileSuffixCreator

...
@Bean
public MultiResourceItemWriter<Customer> multiCustomerFileWriter(CustomerOutputFileSuffixCreator suffixCreator) throws Exception {

    return new MultiResourceItemWriterBuilder<Customer>()
	            .name("multiCustomerFileWriter")
	            .delegate(delegateItemWriter())
	            .itemCountLimitPerResource(25)
	            .resource(new FileSystemResource("Chapter9/target/customer"))
	            .resourceSuffixCreator(suffixCreator)
	            .build();
}
...
