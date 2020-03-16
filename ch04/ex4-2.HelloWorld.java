// Listing 4-2. HelloWorld.java

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
	        .tasklet((contribution, chunkContext) -> {
		    System.out.println("Hello, world!");
		    return RepeatStatus.FINISHED;
		}).build();
    }
    
    public static void main(String[] args) {
	SpringApplication.run(HelloWorldJob.class, args);
    }
}
