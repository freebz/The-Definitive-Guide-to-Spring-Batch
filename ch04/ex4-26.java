// Listing 4-26. Configuring Job Listeners in BatchConfiguration.java

...
@Bean
public Job job() {

    return this.jobBuilderFactory.get("basicJob")
	         .start(step1())
	         .validator(validator())
	         .incrementer(new DailyJobTimestamper())
	         .listener(JobListenerFactoryBean.getListener(
			      new JobLoggerListener()))
	         .build();
}
...
