// Listing 9-62. JmsItemReader and JmsItemWriter and the Job That Uses Them

...
@Bean
public JsmItemReader<Customer> jmsItemReader(JmsTemplate jmsTemplate) {

    return new JmsItemReaderBuilder<Customer>()
	            .jmsTemplate(jmsTemplate)
	            .itemType(Customer.class)
	            .build();
}

@Bean
public JmsItemWriter<Customer> jmsItemWriter(JmsTemplate jmsTemplate) {

    return new JmsItemWriterBuilder<Customer>()
	            .jmsTemplate(jmsTemplate)
	            .build();
}

@Bean
public Step formatInputStep() throws Exception {
    return this.stepBuilderFactory.get("formatInputStep")
	            .<Customer, Customer>chunk(10)
	            .reader(customerFileReader(null))
	            .writer(jmsItemWriter(null))
	            .build();
}

@Bean
public Step formatOutputStep() throws Exception {
    return this.stepBuilderFactory.get("formatOutputStep")
	            .<Customer, Customer>chunk(10)
	            .reader(jmsItemReader(null))
	            .writer(xmlOutputWriter(null))
	            .build();
}

@Bean
public Job jmsFormatJob() throws Exception {
    return this.jobBuilderFactory.get("jmsFormatJob")
	            .start(formatInputStep())
	            .next(formatOutputStep())
	            .build();
}
...
