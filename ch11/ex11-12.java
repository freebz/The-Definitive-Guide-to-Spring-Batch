// Listing 11-12. MultiResourcePartitioner

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
