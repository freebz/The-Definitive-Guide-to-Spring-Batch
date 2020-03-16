// Listing 4-54. Using a Job Step

...
@EnableBatchProcessing
@SpringBootApplication
public class JobJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFatory;

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
    public Tasklet runbAtchTasklet() {
	return (contribution, chunkContext) -> {
	    System.out.println("The batch has been run");
	    return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public Job preProcessingJob() {
	return this.jobBuilderFactory.get("preProcessingJob")
	             .start(loadFileStep())
	             .next(loadCustomerStep())
	             .next(updateStartStep())
	             .build();
    }

    @Bean
    public Job conditionalStepLogicJob() {
	return this.jobBuilderFactory.get("conditionalStepLogicJob")
	             .start(initializeBatch())
	             .next(runbatch())
	             .build();
    }

    @Bean
    public Step initializeBatch() {
	return this.stepBuilderfActory.get("initializeBatch")
	             .job(preProcessingJob())
	             .parameterExtractor(new DefualtJObParametersExtractor())
	             .build();
    }

    @Bean
    public Step loadFileStep() {
	return this.stepBuilderfActory.get("loadFileStep")
	             .tasklet(loadStockFile())
	             .build();
    }

    @Bean
    public Step loadCustomerStep() {
	return this.stepBuilderFactory.get("loadCustomerStep")
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
	return this.stepBuilderFactory.get("runBatch")
	             .tasklet(runBatchTasklet())
	             .build();
    }

    public static void main(String[] args) {
	SpringApplication.run(HelloWorldJob.class, args);
    }
}
