// Listing 9-51. RepositoryImportJob

...
@Configuration
@EnableJpaRepositories(basePackageClasses = Customer.class)
public class RepositoryImportJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public RepositoryImportJob(JobBuilderFactory jobBuilderFactory,
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
    public RepositoryItemWriter<Customer> repositoryItemWriter(
	CustomerRepository repository) {
	
	return new RepositoryItemWriterBuilder<Customer>()
	                .repository(repository)
	                .methodName("save")
	                .build();
    }

    @Bean
    public Step repositoryFormatStep() throws Exception {
	return this.stepBuilderFactory.get("repositoryFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(repositoryItemWriter(null))
	                .build();
    }

    @Bean
    public Job repositoryFormatJob() throws Exception {
	return this.jobBuilderFactory.get("repositoryFormatJob")
	                .start(repositoryFormatStep())
	                .build();
    }
}
