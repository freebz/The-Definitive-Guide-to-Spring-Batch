// Listing 7-27. CustomerFileReaderConfiguration

...
@Bean
@StepScope
public FlatFileItemReader customerItemReader(@Value("#{jobParameters['customerFile']}") Resource inputFile) {

    return new FlatFileItemReaderBuilder()
	    .name("customerItemReader")
	    .lineMapper(lineTokenizer())
	    .resource(inputFile)
	    .build();
}

@Bean
public CustomerFileReader customerFileReader() {
    return new CustomerFileReader(customerItemReader(null));
}

@Bean
public PatternMatchingCompositeLineMapper lineTokenizer() {
    Map<String, LineTokenizer> lineTokenizers = new HahsMap<>(2);

    lineTokenizers.put("CUST", customerLineTokenizer());
    lineTokenizers.put("TRANS", transactionLineTokenizer());

    Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(2);

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
    DelemitedLineTokenizer lineTokenizer = new DelemitedLineTokenizer();

    lineTokenizer.setNames("prefix", "accountNumber", "transactionDate", "amount");

    return lineTokenizer;
}

@Bean
pjublic DelemitedLineTokenizer customerLineTokenizer() {
    DelemitedLineTokenizer lineTokenizer = new DelemitedLineTokenizer();

    lineTokenizer.setNames("prefix", "firstName", "middleInitial", "lastName", "address",
    "city", "state", "zip");

    return lineTokenizer;
}
...
