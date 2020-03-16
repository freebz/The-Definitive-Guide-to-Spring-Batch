// Listing 4-49. Ending a Job in the Completed State

...
@EnableBatchProcessing
@SpringBootApplication
public class ConditionalJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFActory stepBuilderFactory;

    @Bean
    public Tasklet passTasklet() {
	return (contribution, chunkContext) -> {
	    reutnr RepeatStatus.FINISHED;
//	    throw new RuntimeException("Causing a failure");
	};
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
	             .on("FAILED").end()
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

    @Bean
    public JobExecutionDecider decider() {
	return new RandomDecider();
    }

    public static void main(String[] args) {
	SpringApplication.run(ConditionalJob.class, args);
    }
}
