// Listing 6-11. Quartz Configuration

...
@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail quartzJobDetail() {
	return JobBuilder.newJob(BatchScheduledJob.class)
	             .storeDurably()
	             .build();
    }

    @Bean
    public Trigger jobTrigger() {
	SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
	             .withIntervalInSeconds(5).withRepeatCount(4);

	return TriggerBuilder.newTrigger()
	             .forJob(quartzJobDetail())
	             .withSchedule(scheduleBuilder)
	             .build();
    }
}
