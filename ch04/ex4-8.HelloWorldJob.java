// Listing 4-8. Accessing JobParameter in a Spring Configuration

...
@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("basicJob")
	    .start(step1())
	    .build();
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	    .tasklet(helloWorldTasklet())
	    .build();
    }

    @Bean
    public Tasklet helloWorldTasklet() {

	return (contribution, chunkContext) -> {
	    String name = (String) chunkContext.getStepContext()
		.getJobParameters()
		.get("name");

	    System.out.println(String.format("Hello, %s!", name));
	    return RepeatStatus.FINISHED;
	};
    }

    public static void main(String[] args) {
	SpringApplication.run(HelloWorldJob.class, args);
    }
}
