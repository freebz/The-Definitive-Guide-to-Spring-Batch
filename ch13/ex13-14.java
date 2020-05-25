// Listing 13-14. FlatFileItemReaderTests#getStepExecution

...
public StepExecution getStepExecution() {
    JobParameters jobParameters = new JobParametersBuilder()
	            .addString("customerUpdateFile", "classpath:customerUpdateFile.csv")
	            .toJobParameters();

    return MetaDataInstanceFactory.createStepExecution(jobParameters);
}
...
