// Listing 11-4. Configuration of Parallel Steps

@EnableBatchProcessing
@SpringBootApplication
public class ParallelStepsJobApplication {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parallelStepsJob() {
	Flow secondFlow = new FlowBuilder<Flow>("secondFlow")
	                .start(step2())
	                .build();

	Flow parallelFlow = new FlowBuilder<Flow>("parallelFlow")
	                .start(step1())
	                .split(new SimpleAsyncTaskExecutor())
	                .add(secondFlow)
	                .build();

	return this.jobBuilderFactory.get("parallelStepsJob")
	                .start(parallelFlow)
	                .end()
	                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Transaction> fileTransactionReader(
	            @Value("#{jobParameters['inputFlatFile']}") Resource resource) {

	return new FlatFileItemReaderBuilder<Transaction>()
	                .name("flatFileTransactionReader")
	                .resource(resource)
	                .delimited()
	                .names(new String[] {"account", "amount", "timestamp"})
	                .fieldSetMapper(fieldSet -> {
				Transaction transaction = new Transaction();

				transaction.setAccount(fieldSet.readString("account"));
				transaction.setAmount(fieldSet.readBigDecimal("amount"));
				transaction.setTimestamp(fieldSet.readDate("timestamp", "yyyy-MM-dd HH:mm:ss"));

				return timestamp;
			})
	                .build();
    }

    @Bean
    @StepScope
    public StaxEventItemReader<Transaction> xmlTransactionReader(
	            @Value("#{jobParameters['inputXmlFile']}") Resource resource) {
	Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
	unmarshaller.setClassesToBeBound(Transaction.class);

	return new StaxEventItemReaderBuilder<Transaction>()
	                .name("xmlFileTransactionReader")
	                .resource(resource)
	                .addFragmentRootElements("transaction")
	                .unmarshaller(unmarshaller)
	                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Transaction> writer(DataSource dataSource) {
	return new JdbcBatchItemWriterBuilder<Transaction>()
	                .dataSource(dataSource)
	                .beanMapped()
	                .sql("INSERT INTO TRANSACTION (ACCOUNT, AMOUNT, TIMESTAMP) VALUES (:account, :amount, :timestamp)")
	                .build();
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	                .<Transaction, Transaction>chunk(100)
	                .reader(xmlTransactionReader(null))
	                .writer(writer(null))
	                .build();
    }

    @Bean
    public Step step2() {
	return this.stepBuilderFactory.get("step2")
	                .<Transaction, Transaction>chunk(100)
	                .reader(fileTransactionReader(null))
	                .writer(writer(null))
	                .build();
    }

    public static void main(String[] args) {
	String [] newArgs = new String[] {"inputFlatFile=/data/csv/bigtransactions.csv",
					  "inputXmlFile=/data/xml/bigtransactions.xml"};

	SpringApplication.run(ParallelStepsJobApplication.class, newArgs);
    }
}
