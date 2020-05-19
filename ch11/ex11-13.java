// Listing 11-13. TaskExecutorPartitionHandler

...
@Bean
public TaskExecutorPartitionHandler partitionHandler() {
    TaskExecutorPartitionHandler partitionHandler =
	    new TaskExecutorPartitionHandler();

    partitionHandler.setStep(step1());
    partitionHandler.setTaskExecutor(new SimpleAsyncTaskExecutor());

    return partitionHandler;
}
...
