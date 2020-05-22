// Listing 12-4. EnrichmentProcessor.java

...
public class EnrichmentProcessor implements ItemProcessor<Foo, Foo> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Foo process(Foo foo) throws Exception {
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
