// Listing 8-16. upperCaseItemProcessor

...
@Bean
public ItemProcessorAdapter<Customer, Customer> upperCaseItemProcessor(
    UpperCaseNameService service) {

    ItemProcessorAdapter<Customer, Customer> adapter = new ItemProcessorAdapter<>();

    adapter.setTargetObject(service);
    adapter.setTargetMethod("upperCase");

    return adapter;
}
...
