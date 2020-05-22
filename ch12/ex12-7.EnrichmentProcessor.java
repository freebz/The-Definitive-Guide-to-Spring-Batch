// Listing 12-7. EnrichmentProcessor with Circuit Breaker

...
public class EnrichmentProcessor implements ItemProcessor<Foo, Foo> {

    @Autowired
    private RestTemplate restTemplate;

    @Recover
    public Foo fallback(Foo foo) {
	foo.setMessage("error");
	return foo;
    }

    @CircuitBreaker(maxAttempts = 1)
    @Override
    public Foo process(Foo foo) {
	ResponseEntity<String> responseEntity =
	    this.restTemplate.exchange(
		    "http://localhost:8080/enrich",
		    HttpMethod.GET,
		    null,
		    String.class);
	foo.setMessage(responseEntity.getBody());

	return foo;
    }
}
