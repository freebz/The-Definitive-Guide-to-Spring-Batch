// Listing 10-27. statementItemReader

...
@Bean
public JdbcCursorItemReader<Statement> statementItemReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Statement>()
	            .name("statementItemReader")
	            .dataSource(dataSource)
	            .sql("SELECT * FROM CUSTOMER")
	            .rowMapper((resultSet, i) -> {
			    Customer customer =
				    new Customer(resultSet.getLong("customer_id"),
					    resultSet.getString("first_name"),
					    resultSet.getString("middle_name"),
					    resultSet.getString("last_name"),
					    resultSet.getString("address1"),
					    resultSet.getString("address2"),
					    resultSet.getString("city"),
					    resultSet.getString("state"),
					    resultSet.getString("postal_code"),
					    resultSet.getString("ssn"),
					    resultSet.getString("email_address"),
					    resultSet.getString("home_phone"),
					    resultSet.getString("cell_phone"),
					    resultSet.getString("work_phone"),
					    resultSet.getInt("notification_pref"));

			    return new Statement(customer);
		    }).build();
}
...
