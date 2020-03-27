// Listing 6.10. BatchScheduledJob

...
public class BatchScheduledJob extends QuartzJobBean {

    @Autowired
    private Job job;

    @Autowired
    privated JobExplorer jobExplorer;

    @Autowired
    private JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext context) {
	JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer)
	             .getNextJobParameters(this.job)
	             .toJobParameters();

	try {
	    this.jobLauncher.run(this.job, jobParameters);
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
