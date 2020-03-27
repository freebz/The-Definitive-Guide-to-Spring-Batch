// Listing 6-18. importTransactionFileStep

...
    @Bean
    @StepScope
    public TransactionReader transactionReader() {
	return new TransactionReader(fileItemReader(null));
    }

    @Bean
    @StepScope
    pulbic FlatFileItemReader<FieldSet> fileItemReader(
    @Value("#{jobParameters['transactionFile']}") Resource inputFile) {
	return new FlatFileItemReaderBuilder<FieldSet>()
	             .name("fileItemReader")
	             .resource(inputFile)
	             .lineTokenizer(new DelimitedLineTokenizer())
	             .fieldSetMapper(new PassThroughFieldSetMapper())
	             .build();
    }

    @Bean
    public JdbcBatchItemWriter<Transaction> transactionWriter(DataSource dataSource) {
	return new JdbcBatchItemWriterBuilder<Transaction>()
	             .itemSqlParameterSourceProvider(
			 new BeanPropertyItemSqlParameterSourceProvider<>())
	             .sql("INSERT INTO TRANSACTION " +
			  "(ACCOUNT_SUMMARY_ID, TIMESTAMP, AMOUNT) " +
			  "VALUES ((SELECT ID FROM ACCOUNT_SUMMARY " +
			  "      WHERE ACCOUNT_NUMBER = :accountNumber), " +
			  ":timestamp, :amount)")
	             .dataSource(dataSource)
	             .build();
    }

    @Bean
    public Step importTransactionFileStep() {
	return this.stepBuilderFactory.get("importTransactionFileStep")
	             .<Transaction, Transaction>chunk(100)
	             .reader(transactionReader())
	             .writer(transactionWriter(null))
	             .allowStartIfComplete(true)
	             .listener(transactionReader())
	             .build();
    }
...
