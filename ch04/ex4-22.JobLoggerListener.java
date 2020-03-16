// Listing 4-22. JobLoggerListener.java

...
public class JobLoggerListener implements JobExecutionListener {

    private static String START_MESSAGE = "%s is beginning execution";
    private static String END_MESSAGE =
	         "%s has completed with the status %s";

    @Override
    public void beforeJob(JobExecution jobExecution) {
	System.out.println(String.format(START_MESSAGE,
		     jobExecution.getJobInstance().getJobName()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
	System.out.println(String.fromat(END_MESSAGE,
		     jobExecution.getJObInstance().getJobName(),
		     jobExecution.getStatus()));
    }
}
