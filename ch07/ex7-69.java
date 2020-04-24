// Listing 7-69. CustomerItemReader Configuration

...
@Bean
public CustomerItemReader customerItemReader() {
    CustomerItemReader customerItemReader = new CustomerItemReader();

    customerItemReader.setName("customerItemReader");

    return customerItemReader;
}
...
