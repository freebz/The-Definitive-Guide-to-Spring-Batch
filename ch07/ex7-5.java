// Listing 7-5. A Simple Writer

...
@Bean
public ItemWriter<Customer> itemWriter() {
    return (items) -> items.forEach(System.out::println);
}
...
