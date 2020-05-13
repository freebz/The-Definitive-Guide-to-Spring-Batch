// Listing 10-5. LineTokenizer Configurations for the Customer Update File

...
@Bean
public LineTokenizer customerUpdatesLineTokenizer() throws Exception {
    DelimitedLineTokenizer recordType1 = new DelimitedLineTokenizer();

    recordType1.setNames("recordId", "customerId", "firstName",
    "middleName", "lastName");

    recordType1.afterPropertiesSet();

    DelimitedLineTokenizer recordType2 = new DelimitedLineTokenizer();

    recordType2.setNames("recordId", "customerId", "address1",
    "address2", "city", "state", "postalCode");

    recordType2.afterPropertiesSet();

    DelimitedLineTokenizer recordType3 = new DelimitedLineTokenizer();

    recordType3.setNames("recordId", "customerId", "emailAddress",
		"homePhone", "cellPhone", "workPhone", "notificationPreference");

    recordType3.afterPropertiesSet();

    Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
    tokenizers.put("1*", recordType1);
    tokenizers.put("2*", recordType2);
    tokenizers.put("3*", recordType3);

    PatternMatchingCompositeLineTokenizer lineTokenizer =
	        new PatternMatchingCompositeLineTokenizer();

    lineTokenizer.setTokenizers(tokenizers);

    return lineTokenizer;
}
...
