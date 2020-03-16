// Listing 4-30. Adding a Name to the Job's ExecutionContext

...
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
	                .tasklet(helloWorldTasklet())
	                .build();
    }

    @Bean
    public Tasklet helloWorldTasklet() {
	return new HelloWorld();
    }

    public static class HelloWorld implements Tasklet {
	private static final String HELLO_WORLD = "Hello, %s";

	public RepeatStatus execute( StepContribution step,
		        ChunkContext context ) throws Exception {
	    String name =
		                (String) context.getStepContext()
		                                .getJobParameters()
		                                .get("name");

	    ExecutionContext jobContext = context.getStepContext()
		            .getStepExecution()
		            .getExecutionContext();
	    jobContext.put("name", name);

	    System.out.println( String.format(HELLO_WORLD, name) );

	    return RepeatStatus.FINISHED;
	}
    }
}
