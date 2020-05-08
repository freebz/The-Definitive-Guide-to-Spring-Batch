// Listing 9-34. HibernateImportJob.java Using Hibernate

...
@Configuration
public class HibernateImportJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public HibernateImportJob(JobBuilderFactory jobBuilderFactory,
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
    public HibernateItemWriter<Customer> hibernateItemWriter(
	EntityManagerFactory entityManager) {

	return new HibernateItemWriterBuilder<Customer>()
	                .sessionFactory(entityManager.unwrap(SessionFactory.class))
	                .build();
    }

    @Bean
    public Step hibernateFormatStep() throws Exception {
	return this.stepBuilderFactory.get("jdbcFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(hibernateItemWriter(null))
	                .build();
    }

    @Bean
    public Job hibernateFormatJob() throws Exception {
	return this.jobBuilderFactory.get("hibernateFormatJob")
	                .start(hibernateFormatStep())
	                .build();
    }
}
