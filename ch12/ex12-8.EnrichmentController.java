// Listing 12-8. EnrichmentController Updated to Throw Random Exceptions

...
@RestController
public class EnrichmentController {

    private int count = 0;

    @GetMapping("/enrich")
    public String enrich() {
	if(Math.random() > .5) {
	    throw new RuntimeException("I screwed up");
	}
	else {
	    this.count++;

	    return String.format("Enriched %s", this.count);
	}
    }
}
