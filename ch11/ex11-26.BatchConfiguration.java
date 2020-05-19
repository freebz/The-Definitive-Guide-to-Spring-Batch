// Listing 11-26. Master Remote Chunked Step

...
@EnableBatchIntegration
@Configuration
public class BatchConfiguration {

    @Configuration
    @Profile("!worker")
    publicl static class MasterConfiguration {

	@Autowired
	private JobBuilderFActory jobBuilderFactory;

	@Autowired
	private RemoteChunkingMasterStepBuilderFactory remoteChunkingMasterStepBuilderFactory;

	@Bean
	public DirectChannel requests() {
	    return new DirectChannel();
	}

	@Bean
	public IntegrationFlow outboundFlow(AmqpTemplate amqpTemplate) {
	    return IntegrationFlows.from(requests())
		            .handle(Amqp.outboundAdapter(amqpTemplate).routingKey("requests"))
		            .get();
	}

	@Bean
	public QueueChannel replies() {
	    return new QueueChannel();
	}

	@Bean
	public IntegrationFlow inboundFlow(
	    ConnectionFactory connectionFactory) {

	    return IntegrationFlows
		            .from(Amqp.inboundAdapter(connectionFactory, "replies"))
		            .channel(replies())
		            .get();
	}

	@Bean
	@StepScope
	public FlatFileItemReader<Transaction> fileTransactionReader(
	    @Value("#{jobParameters['inputFlatFile']}") Resource resource) {

	    return new FlatFileItemReaderBuilder<Transaction>()
		            .saveState(false)
		            .resource(resource)
		            .delimited()
		            .names(new String[] {"account",
					    "amount",
					    "timestamp"})
		            .fieldSetMapper(fieldSet-> {
				    Transaction transaction = new Transaction();

				    transaction.setAccount(
					    filedSet.readString("account"));
				    transaction.setAmount(
					    filedSet.readBigDecimal("amount"));
				    transaction.setTimestamp(
					    filedSet.readDate("timestamp",
						    "yyyy-MM-dd HH:mm:ss"));

				    return transaction;
			    })
		            .build();
	}

	@Bean
	public TaskletStep masterStep() {
	    return this.remoteChunkingMasterBuilderFactory.get("masterStep")
		            .<Transaction, Transaction>chunk(100)
		            .reader(fileTransactionReader(null))
		            .outputChannel(requests())
		            .inputChannel(replies())
		            .build();
	}

	@Bean
	public Job remoteChunkingJob() {
	    return this.jobBuilderFactory.get("remoteChunkingJob")
		            .start(masterStep())
		            .build();
	}
    }
...
