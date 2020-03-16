// Listing 4-13. Configured Job with JobParameters Validation

...
@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public CompositeJobParametersValidator validator() {
	CompositeJobParametersValidator validator =
	            new CompositeJobParametersValidator();

	DefaultJobParametersValidator defaultJobParametersValidator =
	            new DefaultJobParametersValidator(
			         new String[] {"fileName"}
				 new String[] {"name"});

	defaultJobParametersValidator.afterPropertiesSet();

	validator.setValidators(
	            Arrays.asList(new ParameterValidator(),
		            defaultJobParametersValidator));

	return validator;
    }

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("basicJob")
	             .start(step1())
	             .validator(validator())
	             .build();
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	             .tasklet(helloWorldTasklet(null, null))
	             .build();
    }

    @StepScope
    @Bean
    public Tasklet helloWorldTasklet(
	         @Value("#{jobParameters['name']}") String name,
		 @Value("#{jobParameters['fileName']}") String fileName) {

	return (contribution, chunkContext) -> {

	            System.out.println(
		                 String.format("Hello, %s!", name));
		    System.out.println(
			         String.format("fileName = %s", fileName));

		    return RepeatStatus.FINISHED;
	       };
    }

    public static void main(String[] args) {
	SpringApplication.run(HelloWorldJob.class, args);
    }
}
