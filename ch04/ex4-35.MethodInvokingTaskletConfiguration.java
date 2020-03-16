// Listing 4-35. Using MethodInvokingTaskletAdapter with Parameters

...
@EnableBatchProcessing
@SpringBootApplication
public class MethodInvokingTaskletConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job methodInvokingJob() {
	return this.jobBuilderFactory.get("methodInvokingJob")
	             .start(methodInvokingStep())
	             .build();
    }

    @Bean
    public Step methodInvokingStep() {
	return this.stepBuilderFactory.get("methodInvokingStep")
	             .tasklet(methodInvokingTasklet(null))
	             .build();
    }

    @StepScope
    @Bean
    public MethodInvokingTaskletAdapter methodInvokingTasklet(
	         @Value("#{jobParameters['message']}") String message) {

	MethodInvokingTaskletAdapter methodInvokingTaskletAdapter =
	             new MethodInvokingTaskletAdapter();

	methodInvokingTaskletAdapter.setTargetObject(service());
	methodInvokingTaskletAdapter.setTargetMethod("serviceMethod");
	methodInvokingTaskletAdapter.setArguments(new String[] {message});

	return methodInvokingTaskletAdapter;
    }

    @Bean
    public CustomService service() {
	return new CustomService();
    }

    public static void main(String[] args) {
	SpringApplication.run(MethodInvokingTaskletConfiguration.class, args);
    }
}
