// Listing 9-40. mongoFormatJob

...
@Configuration
public class MongoImportJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public MongoImportJob(JobBuilderFactory jobBuilderFactory,
		    StepBuilderFactory stepBuilderFactory) {

	this.jobBuilderFactory = jobBuilderFactory;
	this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> customerFileReader(
	            @Value("#{jobParameters['customerFile']}")Resource inputFile) {

	return new FlatFileItemReaderBuilder<Customer>()
	                .name("customerFileReader")
	                .resource(inputFile)
	                .delimited()
	                .names(new String[] {"firstName",
					"middleInitial",
					"address",
					"city",
					"state",
					"zip"})
	                .targetType(Customer.class)
	                .build();
    }

    @Bean
    public MongoItemWriter<Customer> mongoItemWriter(MongoOperations mongoTemplate) {
	return new MongoItemWriterBuilder<Customer>()
	                .collection("customers")
	                .template(mongoTemplate)
	                .build();
    }

    @Bean
    public Step mongoFormatStep() throws Exception {
	return this.stepBuilderFactory.get("mongoFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(mongoItemWriter(null))
	                .build();
    }

    @Bean
    public Job mongoFormatJob() throws Exception {
	return this.jobBuilderFactory.get("mongoFormatJob")
	                .start(mongoFormatStep())
	                .build();
    }
}
