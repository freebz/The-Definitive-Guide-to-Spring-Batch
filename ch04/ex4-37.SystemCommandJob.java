// Listing 4-37. Using SystemCommandTasklet

...
@EnableBatchProcessing
@SpringBootApplication
public class SystemCommandJOb {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("systemCommandJob")
	             .start(systemCommandStep())
	             .build();
    }

    @Bean
    public Step systemCommandStep() {
	return this.stepBuilderFactory.get("systemCommandStep")
	             .tasklet(systemCommandTasklet())
	             .build();
    }

    @Bean
    public SystemCommandTasklet systemCommandTasklet() {
	SystemCommandTasklet systemCommandTasklet = new SystemCommandTasklet();

	systemCommandTasklet.setCommand("rm -rf /tmp.txt");
	systemCommandTasklet.setTimeout(5000);
	systemCommandTasklet.setInterruptOnCancel(true);

	return systemCommandTasklet;
    }

    public static void main(String[] args) {
	SpringApplication.run(SystemCommandJob.class, args);
    }
}
