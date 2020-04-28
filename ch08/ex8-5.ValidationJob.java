// Listing 8-5. ValidationJob

...
@EnableBatchProcessing
@SpringBootApplication
public class ValidationJob {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> customerItemReader(
	         @Value("#{jobParameters['customerFile']}")Resource inputFile) {

	return new FlatFileItemReaderBuilder<Customer>()
	             .name("customerItemReader")
	             .delimited()
	             .names(new String[] {"firstName",
				  "middleInitial",
				  "lastName",
				  "address",
				  "city",
				  "state",
				  "zip"})
	             .targetType(Customer.class)
	             .resource(inputFile)
	             .build();
    }

    @Bean
    public ItemWriter<Customer> itemWriter() {
	return (items) -> items.forEach(System.out::println);
    }

    @Bean
    public BeanValidatingItemProcessor<Customer> customerValidatingItemProcessor() {
	return new BeanValidatingItemProcessor<>();
    }

    @Bean
    public Step copyfileStep() {
	
	return this.stepBuilderFactory.get("copyFileStep")
	             .<Customer, Customer>chunk(5)
	             .reader(customerItemReader(null))
	             .processor(customerValidatingItemProcessor())
	             .writer(itemwriter())
	             .build();
    }

    @Bean
    public Job job() throws Exception {

	return this.jobBuilderFactory.get("job")
	             .start(copyFileStep())
	             .build();
    }

    public static void main(String[] args) {
	SpringApplication.run(ValidationJob.class, "customerFile=/input/customer.csv");
    }
}
