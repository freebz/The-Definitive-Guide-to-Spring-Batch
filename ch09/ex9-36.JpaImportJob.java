// Listing 9-36. formatJob Configured to Use JpaItemWriter

...
@Configuration
public class JpaImportJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public JpaImportJob(JobBuilderFactory jobBuilderFactory,
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
					"lastName",
					"address",
					"city",
					"state",
					"zip"})
	                .targetType(Customer.class)
	                .build();
    }

    @Bean
    public JpaItemWriter<Customer> jpaItemWriter(
	EntityManagerFactory entityManagerFactory) {

	JpaItemWriter<Customer> jpaItemWriter = new JpaItemWriter<>();

	jpaItemWriter.setEntityManagerFactory(entityManagerFactory);

	return jpaItemWriter;
    }

    @Bean
    public Step jpaFormatStep() throws Exception {
	return this.stepBuilderFactory.get("jpaFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(jpaItemWriter(null))
	                .build();
    }

    @Bean
    public Job jpaFormatJob() throws Exception {
	return this.jobBuilderFactory.get("jpaFormatJob")
	                .start(jpaFormatStep())
	                .build();
    }
}
