// Listing 4-31. HelloWorld Tasklet

@EnableBatchProcessing
@Configuration
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
	                .tasklet((stepContribution, chunkContext) -> {
				System.out.println("Hello, World!");
				return RepeatStatus.FINISHED;
			})
	                .build();
    }
}
