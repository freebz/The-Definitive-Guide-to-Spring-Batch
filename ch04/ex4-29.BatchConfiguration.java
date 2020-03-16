// Listing 4-29. Adding a Name to the Job's ExecutionContext

...
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("job")
	                .start(step1())
	                .next(step2())
	                .build();
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	                .tasklet(new HelloTasklet())
	                .listener(promotionListener())
	                .build();
    }

    @Bean
    public Step step2() {
	return this.stepBuilderFactory.get("step2")
	                .tasklet(new BoodByeTasklet())
	                .build();
    }

    @Bean
    public StepExecutionListener promotionListener() {
	ExecutionContextPromotionListener listener = new
	                ExecutionContextPromotionListener();

	listener.setKeys(new String[] {"name"});

	return listener;
    }
}
