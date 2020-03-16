// Listing 4-33. Using MethodInvokingTaskletAdapter

...
@EnableBatchProcessing
@SpringBootApplication
public class MethodInvokingTaskletConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job methodInvokeingJob() {
	return this.jobBuilderFactory.get("methodInvokingJob")
	             .start(methodInvokingStep())
	             .build();
    }

    @Bean
    public Step methodInvokingStep() {
	return this.stepBuilderFactory.get("methodInvokingStep")
	             .tasklet(methodInvokingTasklet())
	             .build();
    }

    @Bean
    public MethodInvokingTaskletAdapter methodInvokingTasklet() {
	MethodInvokingTaskletAdapter methodInvokingTaskletAdapter =
	             new MethodInvokingTaskletAdapter();

	methodInvokingTaskletAdapter.setTargetObject(service());
	methodInvokingTaskletAdapter.setTargetMethod("serviceMethod");

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
