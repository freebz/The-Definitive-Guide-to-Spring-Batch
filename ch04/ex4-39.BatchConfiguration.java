// Listing 4-39. BatchConfiguration.java

...
@EnableBatchProcessing
@SpringBootApplication
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("job")
	                .start(step1())
	                .build();
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	                .<String, String>chunk(10)
	                .reader(itemReader(null))
	                .writer(itemWriter(null))
	                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<String> itemReader(
	        @Value("#{jobParameters['inputFile']}") Resource inputFile) {

	return new FlatFileItemReaderBuilder<String>()
	                .name("itemReader")
	                .resource(inputFile)
	                .lineMapper(new PassThroughLineMapper())
	                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<String> itemWriter(
	        @Value("#{jobParameters['outputFile']}") Resource outputFile) {

	return new FlatFileItemWriterBuilder<String>()
	                .name("itemWriter")
	                .resource(outputFile)
	                .lineAggregator(new PassThroughLineAggregator<>())
	                .build();
    }
}
