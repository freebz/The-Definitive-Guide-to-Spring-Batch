// Listing 6-1. A job That Doesn't Run

@EnableBatchProcessing
@SpringBootApplication
public class NoRunJob {

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
			    System.out.println("step1 ran!");
			    return RepeatStatus.FINISHED;
		     }).build();
    }

    public static void main(String[] args) {
	SpringApplication application = new SpringApplication(NoRunJob.class);

	Properties properties = new Properties();
	properties.put("spring.batch.job.enabled", false);
	application.setDefaultProperties(properties);

	application.run(args);
    }
}
