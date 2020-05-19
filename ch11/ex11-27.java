// Listing 11-27. Worker Remote Chunked Step

...
    @Configuration
    @Profile("worker")
    public static class WorkerConfiguration {

	@Autowired
	private RemoteChunkingWorkerBuilder<Transaction, Transaction> workerBuilder;

	@Bean
	public DirectChannel requests() {
	    return new DirectChannel();
	}

	@Bean
	public DirectChannel replies() {
	    return new DirectChannel();
	}

	@Bean
	public IntegrationFlow inboundFlow(ConnectionFactory connectionFactory) {
	    return IntegrationFlows
		            .from(Amqp.inboundAdapter(connectionFactory, "requests"))
		            .channel(requests())
		            .get();
	}

	@Bean
	public IntegrationFlow outboundFlow(AmqpTemplate template) {
	    return IntegrationFlows.from(replies())
		            .handle(Amqp.outboundAdapter(template).routingKey("replies"))
		            .get();
	}

	@Bean
	public IntegrationFlow integrationFlow() {
	    return this.workerBuilder
		            .itemProcessor(processor())
		            .itemWriter(writer(null))
		            .inputChannel(requests())
		            .outputChannel(replies())
		            .build();
	}

	@Bean
	public ItemProcessor<Transaction, Transaction> processor() {
	    return transaction -> {
		System.out.println("processing transaction = " + transaction);
		return transaction;
	    };
	}

	@Bean
	public JdbcBatchItemWriter<Transaction> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<Transaction>()
		            .dataSource(dataSource)
		            .beanMapped()
		            .sql("INSERT INTO TRANSACTION (ACCOUNT, AMOUNT, TIMESTAMP) " +
				     "VALUES (:account, :amount, :timestamp)")
		            .build();
	}
    }
}
