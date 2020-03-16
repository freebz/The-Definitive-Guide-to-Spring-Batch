// Listing 4-44. Logging Step Start and Stop Listeners

...
public class LoggingStepStartStopListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
	System.out.println(stepExecution.getStepName() + " has begun!");
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
	System.out.println(stepExecution.getStepName() + " has ended!");

	return stepExecution.getExitStatus();
    }
}
