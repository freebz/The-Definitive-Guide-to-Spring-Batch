// Listing 9-82. delegateCustomerItemWriter

@Bean
@StepScope
public FlatFileItemWriter<Customer> delegateCustomerItemWriter(CustomerRecordCountFooterCallback footerCallback) throws Execution {
    BeanWrapperFieldExtractor<Customer> fieldExtractor = new BeanWrapperFieldExtractor<>();
    fieldExtractor.setNames(new String[] {"firstName", "lastName", "address", "city", "state", "zip"});
    fieldExtractor.afterPropertiesSet();

    FormatterLineAggregator<Customer> lineAggregator = new FormatterLineAggregator<>();

    lineAggregator.setFormat("%s %s lives at %s %s in %s, %s.");
    lineAggregator.setFieldExtractor(fieldExtractor);

    FlatFileItemWriter<Customer> itemWriter = new FlatFileItemWriter<>();

    itemWriter.setName("delegateCustomerItemWriter");
    itemWriter.setLineAggregator(lineAggregator);
    itemWriter.setAppendAllowed(true);
    itemWriter.setFooterCallback(footerCallback);

    return itemWriter;
}
