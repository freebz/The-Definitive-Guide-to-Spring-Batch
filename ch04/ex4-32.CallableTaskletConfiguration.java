// Listing 4-32. Using CallableTaskletAdapter

...
@EnableBatchProcessing
@SpringBootApplication
public class CallableTaskletConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job callableJob() {
	return this.jobBuilderFactory.get("callableJob")
	             .start(callableStep())
	             .build();
    }

    @Bean
    public Step callableStep() {
	return this.stepBuilderFactory.get("callableStep")
	             .tasklet(tasklet())
	             .build();
    }

    @Bean
    public Callable<RepeatStatus> callableObject() {
	return () -> {
	       System.out.println("This was executed in another thread");

	       return RepeatStatus.FINISHED;
	};
    }

    @Bean
    public CallableTaskletAdapter tasklet() {
	CallableTaskletAdapter callableTaskletAdapter =
	             new CallableTaskletAdapter();

	callableTaskletAdapter.setCallable(callableObject());

	return callableTaskletAdapter;
    }

    public static void main(String[] args) {
	SpringApplication.run(CallableTaskletConfiguration.class, args);
    }
}
