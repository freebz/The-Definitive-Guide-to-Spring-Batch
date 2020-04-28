// Listing 8-18. lowerCaseItemProcessor

...
@Bean
@StepScope
public ScriptItemProcessor<Customer, Customer> lowerCaseItemProcessor(
            @Value("#{jobParameter['script']}") Resource script) {

    ScriptItemProcessor<Customer, Customer> itemProcessor =
	         new ScriptItemProcessor<>();

    itemProcessor.setScript(script);

    return itemProcessor;
}
...
