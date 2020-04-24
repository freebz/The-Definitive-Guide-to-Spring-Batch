// Listing 7-71. Configuring to Skip 10 ParseExceptions

@Bean
public Step copyFileStep() {

    return this.stepBuilderFactory.get("copyFileStep")
	          .<Customer, Customer>chunk(10)
	          .reader(itemReader())
	          .writer(outputWriter(null))
	          .faultTolerant()
	          .skip(ParseException.class)
	          .skipLimit(10)
	          .build();
}
