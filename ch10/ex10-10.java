// Listing 10-10. customerValidatingItemProcessor

...
@Bean
public JdbcBatchItemWriter<CustomerUpdate> customerNameUpdateItemWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<CustomerUpdate>()
	            .beanMapped()
	            .sql("UPDATE CUSTOMER " +
			            "SET FIRST_NAME = COALESCE(:firstName, FIRST_NAME), " +
			            "MIDDLE_NAME = COALESCE(:middleName, MIDDLE_NAME), " +
			            "LAST_NAME = COALESCE(:lastName, LAST_NAME) " +
			            "WHERE CUSTOMER_ID = :customerId")
	            .dataSource(dataSource)
	            .build();
}

@Bean
public JdbcBatchItemWriter<CustomerUpdate> customerAddressUpdateItemWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<CustomerUpdate>()
	            .beanMapped()
	            .sql("UPDATE CUSTOMER SET " +
			            "ADDRESS1 = COALESCE(:address1, ADDRESS1), " +
			            "ADDRESS2 = COALESCE(:address2, ADDRESS2), " +
			            "CITY = COALESCE(:city, CITY), " +
			            "STATE = COALESCE(:state, STATE), " +
			            "POSTAL_CODE = COALESCE(:postalCode, POSTAL_CODE) " +
			            "WHERE CUSTOMER_ID = :customerId")
	            .dataSource(dataSource)
	            .build();
}

@Bean
public JdbcBatchItemWriter<CustomerUpdate> customerContactUpdateItemWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<CustomerUpdate>()
	            .beanMapped()
	            .sql("UPDATE CUSTOMER SET " +
			    "EMAIL_ADDRESS = COALESCE(:emailAddress, EMAIL_ADDRESS), " +
			    "HOME_PHONE = COALESCE(:homePhone, HOME_PHONE), " +
			    "CELL_PHONE = COALESCE(:cellPhone, CELL_PHONE), " +
			    "WORK_PHONE = COALESCE(:workPhone, WORK_PHONE), " +
			    "NOTIFICATION_PREF = COALESCE(:notificationPreferences, NOTIFICATION_PREF) " +
			            "WHERE CUSTOMER_ID = :customerId")
	            .dataSource(dataSource)
	            .build();
}
...
