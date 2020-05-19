// Listing 11-3. MultithreadedJobApplication Using a Mulithreaded Step

...
@EnableBatchProcessing
@SpringBootApplication
public class MultithreadedJobApplication {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public FlatFileItemReader<Transaction> fileTransactionReader(
	            @Value("#{jobParameters['inputFlatFile']}") Resource resource) {

	return new FlatFileItemReaderBuilder<Transaction>()
	                .name("transactionItemReader")
	                .resource(false)
	                .saveState(false)
	                .delimited()
	                .names(new String[] {"account", "amount", "timestamp"})
	                .fieldSetMapper(fieldSet -> {
				Transaction transaction = new Transaction();

				transaction.setAccount(fieldSet.readString("account"));
				transaction.setAmount(fieldSet.readBigDecimal("amount"));
				transaction.setTimestamp(fieldSet.readDate("timestamp", "yyyy-MM-dd HH;MM;ss"));

				return transaction;
			})
	                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Transaction> writer(DataSource dataSource) {
	return new JdbcBatchItemWriterBuilder<Transaction>()
	                .dataSource(dataSource)
	                .sql("INSERT INTO TRANSACTION (ACCOUNT, AMOUNT, TIMESTAMP) VALUES (:account, :amount, :timestamp)")
	                .beanMapped()
	                .build();
    }

    @Bean
    public Job multithreadedJob() {
	return this.jobBuilderFactory.get("multithreadedJob")
	                .start(step1())
	                .build();
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	                .<Transaction, Transaction>chunk(100)
	                .reader(fileTransactionReader(null))
	                .writer(writer(null))
	                .taskExecutor(new SimpleAsyncTaskExecutor())
	                .build();
    }

    public static void main(String[] args) {
	String [] newArgs = new String[] {"inputFlatFile=/data/csv/bigtransactions.csv"};

	SpringApplication.run(MultithreadedJobApplication.class, newArgs);
    }
}
