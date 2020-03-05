// Listing 1-1. Sample Spring Batch Job Definition

@Bean
public AccountTasklet accountTasklet() {
    return new AccountTasklet();
}

@Bean
public Job accountJob() {
    Step accountStep =
	this.stepBuilderFactory
	    .get("accountStep")
	    .tasklet(accountTasklet())
	    .build();

    return this.jobBuilderFactory
	       .get("accountJob")
	       .start(accountStep)
	       .build();
}
