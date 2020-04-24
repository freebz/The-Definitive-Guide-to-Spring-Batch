// Listing 7-31. Configuration to Process Multiple Customer Files

...
@Bean
@StepScope
public MultiResourceItemReader multiCustomerReader(@Value("#{jobParameters['customerFile']}") Resource[] inputFiles) {
    return new MultiResourceItemReaderBuilder<>()
	    .name("multiCustomerReader")
	    .resources(inputFiles)
	    .delegate(customerFileReader())
	    .build();
}

@Bean
public CustomerFileReader customerFileReader() {
    return new CustomerFileReader(customerItemReader());
}

@Bean
public FlatFileItemReader customerItemReader() {
    return new FlatFileItemReaderBuilder()
	    .name("customerItemReader")
	    .lineMapper(lineTokenizer())
	    .build();
}

@Bean
public PatternMatchingCompositeLIneMapper lineTokenizer() {
    Map<String, LineTokenizer> lineTokenizers = new HashMap<>(2);

    lineTokenizers.put("CUST*", customerLineTokenizer());
    lineTokenizers.put("TRANS*", transactionLineTokenizer());

    Map<String, FieldSetMapper> filedSetMappers = new HashMap<>(2);

    BeanWrapperFieldSetMapper<Customer> customerFieldSetMapper = new
    BeanWrapperFieldSetMapper<>();
    customerFieldSetMapper.setTargetType(Customer.class);

    fieldSetMappers.put("CUST*", customerFieldSetMapper);
    fieldSetMappers.put("TRANS*", new TransactionFieldSetMapper());

    PatternMatchingCompositeLineMapper lineMappers = new PatternMatchingCompositeLineMapper();

    lineMappers.setTokenizers(lineTokenizers);
    lineMappers.setFieldSetMappers(fieldSetMappers);

    return lineMappers;
}

@Bean
public DelimitedLineTokenizer transactionLineTokenizer() {
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

    lineTokenizer.setNames("prefix", "accountNumber", "transactionDate", "amount");

    return lineTokenizer;
}

@Bean
public DelimitedLineTokenizer customerLineTokenizer() {
    DelemitedLineTokenizer lineTokenizer = new DelemitedLineTokenizer();

    lineTokenizer.setNames("prefix", "firstName", "middleInitial", "lastName", "address",
    "city", "state", "zip");

    return lineTokenizer;
}
...
