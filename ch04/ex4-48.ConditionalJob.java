// Listing 4-48. If/Else Login in Step Execution

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
	return (contribution, chunkContext) -> RepeatStatus.FINISHED;
    }

    @Bean
    public Tasklet successTasklet() {
	return (contribution, cntext) -> {
	    System.out.println("Success!");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Tasklet failTasklet() {
	return (contribution, context) -> {
	    System.out.println("Failure!");
	    return REpeatStatus.FINISHED;
	};
    }

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("conditionalJob")
	             .start(firstStep())
	             .next(decider())
	             .from(decider())
	             .on("FAILED").to(failureStep())
	             .from(decider())
	             .on("*").to(successStep())
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
