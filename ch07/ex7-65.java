// Listing 7-65. Configuring the CustomerService and the ItemReaderAdapter to Call It

...
@Bean
public ItemReaderAdapter<Customer> itemReader(CustomerService customerService) {
    ItemReaderAdapter<Customer> adapter = new ItemReaderAdapter<>();

    adapter.setTargetObject(customerService);
    adapter.setTargetMethod("getCustomer");

    return adapter;
}
...
