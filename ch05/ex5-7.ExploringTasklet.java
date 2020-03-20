// Listing 5-7. ExploringTasklet

...
public class ExploringTasklet implements Tasklet {

    private JobExplorer explorer;

    public ExploringTasklet(JobExplorer explorer) {
	this.explorer = explorer;
    }

    public RepeatStatus execute(StepContribution stepContribution,
		 ChunkContext chunkContext) {

	String jobName = chunkContext.getStepContext().getJobName();

	List<JobInstance> instances =
	            explorer.getJobInstances(jobName,
				 0,
				 Integer.MAX_VALUE);

	System.out.println(
	            String.format("There are %d job instances for the job %s",
		    instances.size(),
		    jobName));

	System.out.println("They have had the following results");
	System.out.println("************************************");

	for (JobInstance instance : instances) {
	    List<JobExecution> jobExecutions =
		         this.explorer.getJobExecutions(instance);

	    System.out.println(
		         String.format("Instance %d had %d executions",
				      instance.getInstanceId(),
				      jobExecutions.size()));

	    for (JobExecution jobExecution : jobExecutions) {
		   System.out.println(
		                String.format("\tExecution %d resulted in Exit Status %s",
					    jobExecution.getId(),
					    jobExecution.getExitStatus()));
	    }
	}

	return RepeatStatus.FINISHED;
    }
}
