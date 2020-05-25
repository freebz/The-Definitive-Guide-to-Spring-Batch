// Listing 13-21. JobTests#test

...
@Test
public void test() throws Exception {
    JobExecution jobExecution =
	    this.jobLauncherTestUtils.launchJob();

    assertEquals(BatchStatus.COMPLETED,
	    jobExecution.getSTatus());

    StepExecution stepExecution =
	    jobExecution.getStepExecutions().iterator().next();

    assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());
    assertEquals(3, stepExecution.getReadCount());
    assertEquals(3, stepExecution.getWriteCount());
}
...
