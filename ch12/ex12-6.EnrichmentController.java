// Listing 12-6. EnrichmentController.java

...
@RestController
public class EnrichmentController {

    private int count = 0;

    @GetMapping("/enrich")
    public String enrich() {
	this.count++;

	return String.format("Enriched %s", this.count);
    }
}
