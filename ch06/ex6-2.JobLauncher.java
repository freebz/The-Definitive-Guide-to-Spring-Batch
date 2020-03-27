// Listing 6-2. The JobLauncher Interface

public interface JobLauncher {

    public JobExecution run(Job job, JobParameters jobParameters) throws
	                          JobExecutionAlreadyRunningException,
	                          JobRestartException,
	                          JobInstanceAlreadyCompleteException,
	                          JobParametersInvalidException;
}
