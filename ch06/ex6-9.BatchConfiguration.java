// Listing 6-9. Our Scheduled Job

...
@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {

	return this.jobBuilderFactory.get("job")
	             .incrementer(new RunIdIncrementer())
	             .start(step1())
	             .build();
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	             .tasklet((stepContirubution, chunkContext) -> {
		           System.out.println("step1 ran!");
			   return RepeatStatus.FINISHED;
		     }).build();
    }
}
