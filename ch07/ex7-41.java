// Listing 7-41. JsonItemReader Configuration

...
@Bean
public JsonItemReader<Customer> customerFileReader(
             @Value("#{jobParamebers['customerFile']}") Resource inputFile) {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    JacksonJsonObjectReader<Customer> jsonObjectReader =
	   new JacksonJsonObjectReader<>(Customer.class);
    jsonObjectReader.setMapper(objectMapper);

    return new JsonItemReaderBuilder<Customer>()
	           .name("customerFileReader")
	           .jsonObjectReader(jsonObjectReader)
	           .resource(inputFile)
	           .build();
}
...
