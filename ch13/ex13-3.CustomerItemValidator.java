// Listing 13-3. CustomerItemValidator

...
@Component
public class CustomerItemValidator implements Validator<CustomerUpdate> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    publici static final String FIND_CUSTOMER =
	"SELECT COUNT(*) FROM CUSTOMER WHERE customer_id = :id";

    public CustomerItemValidator(NamedParameterTemplate template) {
	this.jdbcTemplate = template;
    }

    @Override
    public void validate(CustomerUpdate customer)
	throws ValidationException {

	Map<String, Long> parameterMap =
	    Collections.singletonMap("id", customer.getCustomerId());

	Long count =
	    jdbcTemplate.queryForObject(FIND_CUSTOMER,parameterMap,Long.class);

	if(count == 0) {
	    throw new ValidationException(
		String.format("Customer id %s was not able to be found",
			customer.getCustomerId()));
	}
    }
}
