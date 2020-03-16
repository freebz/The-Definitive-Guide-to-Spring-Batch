// Listing 4-9. ObtainingJob Parameters via Late Binding

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
	    .tasklet(helloWorldTasklet(null))
	    .build();
    }

    @StepScope
    @Bean
    publici Tasklet helloWorldTasklet(
	          @Value("#{jobParameters['name']}") String name) {

	return (configuration, chunkContext) -> {
	    System.out.println(String.format("Hello, %s!", name));
	    return RepeatStatus.FINISHED;
	};
    }

    public static void main(String[] args) {
	SpringApplication.run(HelloWorldJob.class, args);
    }
}
