// Listing 6-7. Incrementing Job Parameters Before a Launch

...
@Bean
public Job job() {
    return this.jobBuilderFactory.get("job")
	         .incrementer(new RunIdIncrementer())
	         .start(step1())
	         .build();
}
...
@RestController
public static class JobLaunchingController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JobExplorer jobExplorer;

    @PostMapping(path = "/run")
    public ExitStatus runJob(@RequestBody JobLauncherRequest request) throws Exception {

	Job job = this.context.getBean(request.getName(), Job.class);

	JobParameters jobParameters =
	            new JobParametersBuilder(request.getJobParameters(),
				       this.jobExplorer)
	                         .getNextJobParameters(job)
	                         .toJobParameters();

	return this.jobLauncher.run(job, jobParameters).getExitStatus();
    }
}
...
