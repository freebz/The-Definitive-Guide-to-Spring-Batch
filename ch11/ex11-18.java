// Listing 11-18. Inbound Flow

...
    @Bean
    @StepScope
    public MultiResourcePartitioner partitioner(
	    @Value("#{jobParameters['inputFiles']}") Resource[] resources) {

	MultiResourcePartitioner partitioner = new MultiResourcePartitioner();

	partitioner.setKeyName("file");
	partitioner.setResources(resources);

	return partitioner;
    }

    @Bean
    public Step masterStep() {
	return this.masterStepBuilderFactory.get("masterStep")
	                .partitioner("workerStep", partitioner(null))
	                .outputChannel(requests())
	                .inputChannel(replies())
	                .build();
    }

    @Bean
    public Job remotePartitioningJob() {
	return this.jobBuilderFactory.get("remotePartitioningJob")
	                .start(masterStep())
	                .build();
    }
}
