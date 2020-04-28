// Listing 8-14. ScriptItemProcessor Configuration

...
@Bean
@StepScope
public ScriptItemProcessor<Customer, Customer> itemProcessor(
      @Value("#{jobParameters['script']}") Resource script) {

      ScriptItemProcessor<Customer, Customer> itemProcessor =
	     new ScriptItemProcessor<>();

      itemProcessor.setScript(script);

      return itemProcessor;
}
...
