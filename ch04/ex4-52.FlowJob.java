// Listing 4-52. Defining a Flow

...
@EnableBatchProcessing
@SpringBootApplication
public class FlowJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Tasklet loadStockFile() {
	return (contribution, chunkContext) -> {
	    System.out.println("The stock file has been loaded");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Tasklet loadCustomerFile() {
	return (contribution, chunkContext) -> {
	    System.out.println("The customer file has been loaded");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Tasklet updateStart() {
	return (contribution, chunkContext) -> {
	    System.out.println("The start has been updated");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Tasklet runBatchTasklet() {
	return (contribution, chunkContext) -> {
	    System.out.println("The batch bas been run");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Flow preProcessingFlow() {
	return new FlowBuilder<Flow>("preProcessingFlow").start(loadFileStep())
	             .next(loadCustomerStep())
	             .next(updateStartStep())
	             .build();
    }

    @Bean
    public Job conditionalStepLogicJob() {
	return this.jobBuilderFactory.get("conditionalStepLogicJob")
	             .start(preProcessingFlow())
	             .next(runBAtch())
	             .end()
	             .build();
    }

    @Bean
    public Step loadFileStep() {
	return this.stepBuilderFActory.get("loadFileStep")
	             .tasklet(loadStockFile())
	             .build();
    }

    @Bean
    public Step loadCustomerStep() {
	return this.stepBuilderFActory.get("loadCustomerStep")
	             .tasklet(loadCustomerFile())
	             .build();
    }

    @Bean
    public Step updateStartStep() {
	return this.stepBuilderFactory.get("updateStartStep")
	             .tasklet(updateStart())
	             .build();
    }

    @Bean
    public Step runBatch() {
	return this.stepBuilderFActory.get("runBatch")
	             .tasklet(runBatchTasklet())
	             .build();
    }

    public static void main(String[] args) {
	SpringApplication.run(HelloWorldJob.class, args);
    }
}
