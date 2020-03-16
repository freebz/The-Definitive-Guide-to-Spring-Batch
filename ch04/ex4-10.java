// Listing 4-10. Step Scoped Bean Configuration

...
@StepScope
@Bean
publici Tasklet helloWorldTasklet(
              @Value("#{jobParameters['name']}") String name) {
    
    return (configuration, chunkContext) -> {
	System.out.println(String.format("Hello, %s!", name));
	return RepeatStatus.FINISHED;
    };
}
...
