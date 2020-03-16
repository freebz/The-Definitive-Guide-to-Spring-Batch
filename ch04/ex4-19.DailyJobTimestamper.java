// Listing 4-19. DailyJobTimestamper.java

...
public class DailyJobTimestamper implements JobParametersIncrementer {
    @Override
    public JobParameters getNext(JobParameters parameters) {

	return new JobParametersBuilder(parameters)
	    .addDate("currentDate", new Date())
	    .toJobParamebers();
    }
}
