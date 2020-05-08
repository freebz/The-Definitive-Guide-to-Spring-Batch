// Listing 9-47. GemfireImportJob

...
@Configuration
@PeerCacheApplication(name = "AccessingDataGemFireApplication", logLevel = "info")
public class GemfireImportJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public GemfireImportJob(JobBuilderFactory jobBuilderFactory,
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
    public GemfireItemWriter<Long, Customer> gemfireItemWriter(
	GemfireTemplate gemfireTemplate) {
	
	return new GemfireItemWriterBuilder<Long, Customer>()
	                .template(gemfireTemplate)
	                .itemKeyMapper(new SpELItemKeyMapper<>(
			        "firstName + middleInitial + lastName"))
	                .build();
    }

    @Bean
    public Step gemfireFormatStep() throws Exception {
	return this.stepBuilderFactory.get("gemfireFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(gemfireItemWriter(null))
	                .build();
    }

    @Bean
    public Job gemfireFormatJob() throws Exception {
	return this.jobBuilderFactory.get("gemfireFormatJob")
	                .start(gemfireFormatStep())
	                .build();
    }

    @Bean(name="customer")
    public Region<Long, Customer> getCustomer(final GemFireCache cache) throws Exception {
	LocalRegionFactoryBean<Long, Customer> customerRegion =
	        new LocalRegionFactoryBean<>();
	customerRegion.setCache(cache);
	customerRegion.setName("customer");
	customerRegion.afterPropertiesSet();
	Region<Long, Customer> object = customerRegion.getRegion();
	return object;
    }

    @Bean
    public GemfireTemplate gemfireTemplate() throws Exception {
	return new GemfireTemplate(getCustomer(null));
    }

    @Bean
    public CommandLineRunner validator(final GemfireTemplate gemfireTemplate) {
	return args -> {
	    List<Object> customers =
		gemfireTemplate.find("select * from /customer").asList();
	    
	    for (Object customer : customers) {
		System.out.println(">> object: " + customer);
	    }
	};
    }
}
