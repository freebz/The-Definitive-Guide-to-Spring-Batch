// Listing 7-22. Configuring the customerFileReader with Multiple Record Formats

...
@Bean
@StepScope
public FlatFileItemReader customerItemReader(
      @Value("#{jobParameters['customerFile']}")Resource inputFile) {

    return new FlatFileItemReaderBuilder<Customer>()
	    .name("customerItemReader")
	    .lineMapper(lineTokenizer())
	    .resource(inputFile)
	    .build();
}

@Bean
public PatternMatchingCompositeLineMapper lineTokenizer() {
    Map<String, LineTokenizer> lineTokenizers = new HashMap<>(2);

    lineTokenizers.put("CUST", customerLineTokenizer());
    lineTokenizers.put("TRANS", transactionLineTokenizer());

    Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(2);

    BeanWrapperFieldSetMapper<Customer> customerFieldSetMapper =
      new BeanWrapperFieldSetMapper<>();
    customerFieldSetMapper.setTargetType(Customer.class);

    fieldSetMappers.put("CUST", customerFieldSetMapper);
    fieldSetMappers.put("TRANS", new TransactionFieldSetMapper());

    PatternMatchingCompositeLineMapper lineMappers =
      new PatternMatchingCompositeLineMapper();

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
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

    lineTokenizer.setNames("firstName",
       "middleInitial",
       "lastName",
       "address",
       "city",
       "state",
       "zip");

    lineTokenizer.setIncludedFields(1, 2, 3, 4, 5, 6, 7);

    return lineTokenizer;
}
...
