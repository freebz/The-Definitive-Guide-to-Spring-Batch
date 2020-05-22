// Listing 12-18. EnrichmentProcessor

...
public class EnrichmentProcessor implements ItemProcessor<Foo, Foo> {

    @Autowired
    private RestTemplate restTemplate;

    @Recover
    public Foo fallback(Foo foo) {
	foo.setMessage("error");
	return foo;
    }

    @CircuitBreaker
    @Override
    public Foo process(Foo foo) {
	ResponseEntity<String> responseEntity = this.restTemplate.exchange(
	                "http://rest-service/enrich",
			HttpMethod.GET,
			null,
			String.class);
	foo.setMessage(responseEntity.getBody());

	return foo;
    }
}
