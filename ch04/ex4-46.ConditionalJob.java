// Listing 4-46. If/Else Logic in Step Execution

...
@EnableBatchProcessing
@SpringBootApplication
public class ConditionalJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Tasklet passTasklet() {
	return (contribution, chunkContext) -> {
	    return RepeatStatus.FINISHED;
	    //	               throw new RuntimeException("This is a failure");
	}
	;
    }

    @Bean
    public Tasklet successTasklet() {
	return (contribution, context) -> {
	    System.out.println("Success!");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Tasklet failTasklet() {
	return (contribution, context) -> {
	    System.out.println("Failure!");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("conditionalJob")
	             .start(firstStep())
	             .on("FAILED").to(failureStep())
	             .from(firstStep()).on("*").to(successStep())
	             .end()
	             .build();
    }

    @Bean
    public Step firstStep() {
	return this.stepBuilderFactory.get("firstStep")
	             .tasklet(passTasklet())
	             .build();
    }

    @Bean
    public Step successStep() {
	return this.stepBuilderFactory.get("successStep")
	             .tasklet(successTasklet())
	             .build();
    }

    @Bean
    public Step failureStep() {
	return this.stepBuilderFactory.get("failureStep")
	             .tasklet(failTasklet())
	             .build();
    }

    public static void main(String[] args) {
	SpringApplication.run(ConditionalJob.class, args);
    }
}
