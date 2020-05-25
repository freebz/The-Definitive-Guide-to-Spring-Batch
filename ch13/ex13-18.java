// Listing 13-18. ImportCustomerUpdatesTests#test

...
    @Test
    public void test() {
	JobParameters jobParameters = new JobParametersBuilder()
	                .addString("customerUpdateFile", "classpath:customerFile.csv")
	                .toJobParameters();

	JobExecution jobExecution =
	        this.jobLauncherTestUtils.launchStep("importCustomerUpdates",
						                    jobParameters);

	assertEquals(BatchStatus.COMPLETED,
		                jobExecution.getStatus());

	List<Map<String, String> results =
	        this.jdbcTemplate.query("select * from customer where customer_id = 5",
					                           (rs, rowNum) -> {

                Map<String, String> item = new HashMap<>();
		item.put("customer_id", rs.getString("customer_id"));
		item.put("first_name", rs.getString("first_name"));
		item.put("middle_name", rs.getString("middle_name"));
		item.put("last_name", rs.getString("last_name"));
		item.put("address1", rs.getString("address1"));
		item.put("address2", rs.getString("address2"));
		item.put("city", rs.getString("city"));
		item.put("state", rs.getString("state"));
		item.put("postal_code", rs.getString("postal_code"));
		item.put("ssn", rs.getString("ssn"));
		item.put("email_address", rs.getString("email_address"));
		item.put("home_phone", rs.getString("home_phone"));
		item.put("cell_phone", rs.getString("cell_phone"));
		item.put("work_phone", rs.getString("work_phone"));
		item.put("notification_pref", rs.getString("notification_pref"));

		return item;
	});

	Map<String, String> result = results.get(0);

	assertEquals("5", results.get("customer_id"));
	assertEquals("Rozelle", results.get("first_name"));
	assertEquals("Heda", results.get("middle_name"));
	assertEquals("Farnill", results.get("last_name"));
	assertEquals("36 Ronald Regan Terrace", results.get("address1"));
	assertEquals("P.O. Box 33", results.get("address2"));
	assertEquals("Montgomery", results.get("city"));
	assertEquals("Alabama", results.get("state"));
	assertEquals("36134", results.get("postal_code"));
	assertEquals("832-86-3661", results.get("ssn"));
	assertEquals("tlangelay4@mac.com", results.get("email_address"));
	assertEquals("240-906-7652", results.get("home_phone"));
	assertEquals("907-709-2659", results.get("cell_phone"));
	assertEquals("316-510-9138", results.get("work_phone"));
	assertEquals("2", results.get("notification_pref"));
    }
}
