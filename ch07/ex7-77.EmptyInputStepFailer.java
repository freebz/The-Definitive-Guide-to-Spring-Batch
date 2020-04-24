// Listing 7-77. EmptyInputStepFailer

...
public class EmptyInputStepFailer {

    @AfterStep
    public ExitStatus afterStep(StepExecution execution) {
	if(execution.getReadCount() > 0) {
	    return execution.getExitStatus();
	} else {
	    return ExitStatus.FAILED;
	}
    }
}
