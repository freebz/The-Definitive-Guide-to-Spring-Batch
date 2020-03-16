// Listing 4-27. Adding a Name to the Job's ExecutionContext

...
public class HelloWorld implements Tasklet {
    private static final String HELLO_WORLD = "Hello, %s";

    public RepeatStatus execute( StepContribution step,
				 ChunkContext context ) throws Exception {
	String name = (String) context.getStepContext()
	                    .getJobParameters()
	                    .get("name");

	ExecutionContext jobContext = context.getStepContext()
	                                     .getStepExecution()
	                                     .getJobExecution()
	                                     .getExecutionContext();
	jobContext.put("user.name", name);

	System.out.println( String.format(HELLO_WORLD, name) );
	return RepeatStatus.FINISHED;
    }
}
