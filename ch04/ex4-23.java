// Listing 4-23. Job using the JobLoggerListener

...
@Bean
public Job job() {

    return this.jobBuilderFactory.get("basicJob")
         	 .start(step1())
	         .validator(validator())
	         .incrementer(new DailyJobTimestamper())
	         .listener(new JobLoggerListener())
	         .build();
}
...
