// Listing 9-19. Configuration for formatJob with StaxEventItemWriter

...
@Configuration
public class XmlFileJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public XmlFileJob(JobBuilderFactory jobBuilderFactory,
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
    @StepScope
    public StaxEventItemWriter<Customer> xmlCustomerWriter(
	    @Value("#{jobParameters['outputFile']}") Resource outputFile) {

	Map<String, Class> aliases = new HashMap<>();
	aliases.put("customer", Customer.class);

	XStreamMarshaller marshaller = new XStreamMarshaller();

	marshaller.setAliases(aliases);

	marshaller.afterPropertiesSet();

	return new StaxEventItemWriterBuilder<Customer>()
	                .name("customerItemWriter")
	                .resource(outputFile)
	                .marshaller(marshaller)
	                .rootTagName("customers")
	                .build();
    }

    @Bean
    public Step xmlFormatStep() throws Exception {
	return this.stepBuilderFactory.get("xmlFormatStep")
	                .<Customer, Customer>chunk(10)
	                .reader(customerFileReader(null))
	                .writer(xmlCustomerWriter(null))
	                .build();
    }

    @Bean
    public Job xmlFormatJob() throws Exception {
	return this.jobBuilderFactory.get("xmlFormatJob")
	                .start(xmlFormatStep())
	                .build();
    }
}
