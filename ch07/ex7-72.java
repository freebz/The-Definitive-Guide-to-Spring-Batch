// Listing 7-72. Configuring to Skip All Exceptions Except the ParseException

@Bean
public Step copyFileStep() {

    return this.stepBuilderFactory.get("copyFileStep")
	          .<Customer, Customer>chunk(10)
	          .reader(itemReader())
	          .writer(outputWriter(null))
	          .faultTolerant()
	          .skip(Exception.class)
	          .noSkip(ParseException.class)
	          .skipLimit(10)
	          .build();
}
