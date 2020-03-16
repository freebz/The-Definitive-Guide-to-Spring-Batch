// Listing 4-47. RandomDecider

...
public class RandomDecider implements JobExecutionDecider {

    private Random random = new Random();

    public FlowExecutionStatus decide(JobExecution jobExecution,
	    StepExecution stepExecution) {

	if (random.nextBoolean()) {
	    return new
		FlowExecutionStatus(FlowExecutionStatus.COMPLETED.getName());
	} else {
	    return new
		FlowExecutionStatus(FlowExecutionStatus.FAILED.getName());
	}
    }
}
