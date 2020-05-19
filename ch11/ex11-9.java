// Listing 11-9. Async ItemProcessor

...
@Bean
public AsyncItemProcessor<Transaction, Transaction> asyncItemProcessor() {
    AsyncItemProcessor<Transaction, Transaction> processor = new AsyncItemProcessor();

    processor.setDelegate(processor());
    processor.setTaskExecutor(new SimpleAsyncTaskExecutor());

    return processor;
}

@Bean
public ItemProcessor<Transaction, Transaction> processor() {
    return (transaction) -> {
	Thread.sleep(5);
	return transaction;
    };
}
...
