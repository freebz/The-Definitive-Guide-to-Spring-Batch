// Listing 2-1. The "Hello, World!" Job

@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldApplication {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step() {
	return this.stepBuilderFactory.get("step1")
	                    .tasklet(new Tasklet() {
				    @Override
				    public RepeatStatus execute(StepContribution contribution,
								             ChunkContext
								             chunkContext) {
					System.out.println("Hello, World");
					return RepeatStatus.FINISHED;
				    }
			    }).build();
    }

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("job")
	                    .start(step())
	                    .build();
    }

    public static void main(String[] args) {
	SpringApplication.run(HelloWorldApplication.class, args);
    }
}
