// Listing 5-6. Configuration of an ExploringTasklet Tasklet and JobExplorer

...
@EnableBatchProcessing
@SpringBootApplication
public class DemoApplication {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobExplorer jobExplorer;

    @Bean
    public Tasklet explorerTasklet() {
	return new ExploringTasklet(this.jobExplorer);
    }

    @Bean
    public Step explorerStep() {
	return this.stepBuilderFactory.get("explorerStep")
	              .tasklet(explorerTasklet())
	              .build();
    }

    @Bean
    public Job explorerJob() {
	return this.jobBuilderFactory.get("explorerJob")
	              .start(explorerStep())
	              .build();
    }

    public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
    }

}
