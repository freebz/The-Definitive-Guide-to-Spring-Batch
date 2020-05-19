// Listing 11-10. Async ItemWriter

...
@Bean
public JdbcBatchItemWriter<Transaction> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Transaction>()
	            .dataSource(dataSource)
	            .beanMapped()
	            .sql("INSERT INTO TRANSACTION (ACCOUNT, AMOUNT, TIMESTAMP) " +
			    "VALUES (:account, :amount, :timestamp)")
	            .build();
}

@Bean
public AsyncItemWriter<Transaction> asyncItemWriter() {
    AsyncItemWriter<Transaction> writer = new AsyncItemWriter<>();

    writer.setDelegate(writer(null));

    return writer;
}

@Bean
public Step step1async() {
    return this.stepBuilderFactory.get("step1async")
	            .<Transaction, Future<Transaction>chunk(100)
	            .reader(fileTransactionReader(null))
	            .processor(asyncItemProcessor())
	            .writer(asyncItemWriter())
	            .build();
}

@Bean
public Job asyncJob() {
    return this.jobBuilderFactory.get("asyncJob")
	            .start(step1async())
	            .build();
}
...
