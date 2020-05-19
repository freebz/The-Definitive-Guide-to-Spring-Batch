// Listing 11-14. partitionedMaster Step and Job

...
@Bean
public Step partitionedMaster() {
    return this.stepBuilderFactory.get("step1")
	            .partitioner(step1().getName(), partitioner(null))
	            .partitionHandler(partitionHandler())
	            .build();}
}

@Bean
public Job partionedJob() {
    return this.jobBuilderFactory.get("partitionedJob")
	            .start(partitionedMaster())
	            .build();
}
...
