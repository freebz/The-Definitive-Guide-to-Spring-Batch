// Listing 9-69. Step 2 and the Job Configuration

...
@Bean
public JdbcCursorItemReader<Customer> customerCursorItemReader(DataSource dataSource) {

    return new JdbcCursorItemReaderBuilder<Customer>()
	            .name("customerItemReader")
	            .dataSource(dataSource)
	            .sql("select * from customer")
	            .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
	            .build();
}

@Bean
public SimpleMailMessageItemWriter emailItemWriter(MailSender mailSender) {
    return new SimpleMailMessageItemWriterBuilder()
	            .mailSender(mailSender)
	            .build();
}


@Bean
public Step importStep() throws Exception {
    return this.stepBuilderFactory.get("importStep")
	            .<Customer, Customer>chunk(10)
	            .reader(customerEmailFileReader(null))
	            .writer(customerBatchWriter(null))
	            .build();
}

@Bean
public Step emailStep() throws Exception {
    return this.stepBuilderFactory.get("emailStep")
	            .<Customer, SimpleMailMessage>chunk(10)
	            .reader(customerCursorItemReader(null))
	            .processor((ItemProcessor<Customer, SimpleMailMessage>) customer -> {
			    SimpleMailMessage mail = new SimpleMailMessage();
			    
			    mail.setFrom("prospringbatch@gmail.com");
			    mail.setTo(customer.getEmail());
			    mail.setSubject("Welcome!");
			    mail.setText(String.format("Welcome %s %s,\nYou were imported into the system suing Spring Batch!",
					    customer.getFirstName(), customer.getLastName()));

			    return mail;
		    })
	            .writer(emailItemWriter(null))
	            .build();
}

@Bean
public Job emailJob() throws Exception {
    return this.jobBuilderFactory.get("emailJob")
	            .start(importStep())
	            .next(emailStep())
	            .build();
}
...
