// Listing 10-3. Definition of Import Job

...
@Configuration
public class ImportJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
	return this.jobBuilderFactory.get("importJob")
	                .start(importCustomerUpdates())
	                .build();
    }

    @Bean
    public Step importCustomerUpdates() throws Exception {
	return this.stepBuilderFactory.get("importCustomerUpdates")
	                .<CustomerUpdate, CustomerUpdate>chunk(100)
	                .reader(customerUpdateItemReader(null))
	                .processor(customerValidatingItemProcessor(null))
	                .writer(customerUpdateItemWriter())
	                .build();
    }
...
