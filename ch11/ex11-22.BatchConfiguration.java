// Listing 11-22. BatchConfiguration

...
@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ConfigurableApoplicationContext context;

    @Bean
    @Profile("master")
    public DeployerPartitionHandler partitionHandler(TaskLauncher taskLauncher,
		    JobExplorer jobExplorer,
		    ApplicationContext context,
		    Environment environment) {
	Resource resource =
	        context.getResource("file:///path-to-jar/partitoned-demo-0.0.1-SNAPSHOT.jar");

	DeployerPartitionHandler partitionHandler =
	    new DeployerPartitionHandler(taskLauncher, jobExplorer, resource, "step1");

	List<String> commandLineArgs = new ArrayList<>(3);
	commandLineArgs.add("--spring.profiles.active=worker");
	commandLineArgs.add("--spring.cloud.task.initialize.enabled=false");
	commandLineArgs.add("--spring.batch.initializer.enabled=false");
	commandLineArgs.add("--spring.datasource.initializer=false");
	partitionHandler.setCommandLineArgsProvider(
	        new PassThroughCommandLineArgsProvider(commandLineArgs));
	partitionHandler.setEnvironmentVariablesProvider(
	    new SimpleEnvironmentVariablesProvider(environment));
	partitionHandler.setMaxWorkers(3);
	partitionHandler.setApplicationName("PartitionBatchJobTask");

	return partitionHandler;
    }

    @Bean
    @Profile("worker")
    public DeployStepExecutionHandler stepExecutionHandler(JobExplorer jobExplorer) {
	return new DeployStepExecutionHandler(this.context, jobExplorer, this.jobRepository);
    }
...
