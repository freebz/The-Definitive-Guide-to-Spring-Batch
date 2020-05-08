// Listing 9-7. FormattedTextFileJob.java

...
@EnableBatchProcessing
@Configuration
public class FormattedTextFileJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public FormattedTextFileJob(JobBuilderFactory jobBuilderFactory,
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
    public FlatFileItemWriter<Customer> customerItemWriter(
	    @Value("#{jobParameters['outputFile']}") Resource outputFile) {
	return new FlatFileItemWriterBuilder<Customer>()
	                .name("customerItemWriter")
	                .resource(outputFile)
	                .formatted()
	                .format("%s %s lives at %s %s in %s, %s.")
	                .names(new String[] {"firstName",
				"lastName",
				"city",
				"state",
				"zip"})
	                .build();
    }

    @Bean
    public Step formatStep() {
	return this.stepBuilderFactory.get("formatStep")
	        .<Customer, Customer>chunk(10)
	        .reader(customerFileReader(null))
	        .writer(customerItemWriter(null))
	        .build();
    }

    @Bean
    public Job formatJob() {
	return this.jobBuilderFactory.get("formatJob")
	        .start(formatStep())
	        .incrementer(new RunIdIncrementer())
	        .build();
    }
}
