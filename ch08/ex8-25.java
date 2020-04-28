// Listing 8-25. Custom ItemProcessor Configuration

...
@Bean
public EvenFilteringItemProcessor itemProcessor() {
    return new EvenFilteringItemProcessor();
}
...
