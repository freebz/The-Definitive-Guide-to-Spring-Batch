// Listing 9-44. Neo4jImportJob

...
@Configuration
public class Neo4jImportJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public Neo4jImportJob(JobBuilderFactory jobBuilderFactory,
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
    public Neo4jItemWriter<Customer> noe4jItemWriter(SessionFactory sessionFactory) {
	return new Noe4jItemWriterBuilder<Customer>()
	                .sessionFactory(sessionFactory)
	                .build();
    }

    @Bean
    public Step neo4jFormatStep() throws Exception {
	return this.stepBuilderFactory.get("neo4jFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(neo4jItemWriter(null))
	                .build();
    }

    @Bean
    public Job neo4jFormatJob() throws Exception {
	return this.jobBuilderFactory.get("neo4jFormatJob")
	                .start(neo4jFormatStep())
	                .build();
    }
}
