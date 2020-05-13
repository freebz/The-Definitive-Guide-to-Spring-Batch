// Listing 10-19. transactionItemReader

...
@Bean
@StepScope
public StaxEventItemReader<Transaction> transactionItemReader(
    @Value("#{jobParameters['transactionFile']}") Resource transactionFile) {

    Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
    unmarshaller.setClassesToBeBound(Transaction.class);

    return new StaxEventItemReaderBuilder<Transaction>()
	            .name("fooReader")
	            .resource(transactionFile)
	            .addFragmentRootElements("transaction")
	            .unmarshaller(unmarshaller)
	            .build();
}
...
