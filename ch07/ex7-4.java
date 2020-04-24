// Listing 7-4. customerReader in BatchConfiguration

...
@Bean
@StepScope
public FlatFileItemReader<Customer> customerItemReader(
@Value("#{jobParameters['customerFile']}") Resource inputFile) {

    return new FlatFileItemReaderBuilder<Customer>()
	        .name("customerItemReader")
	        .resource(inputFile)
	        .fixedLength()
	     .columns(new Range[]{new Range(1,11), new Range(12, 12), new Range(13, 22),
		    new Range(23, 26), new Range(27,46), new Range(47,62),
		    new Range(63,64), new Range(65,69)})
	     .names(new String[] {"firstName", "middleInitial", "lastName",
			"addressNumber", "street", "city", "state","zipcode"})
	     .targetType(Customer.class)
	     .build();
    }
...
